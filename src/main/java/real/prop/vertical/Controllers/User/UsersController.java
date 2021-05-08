package real.prop.vertical.Controllers.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import real.prop.vertical.Repository.User.UserRepository;
import real.prop.vertical.Resources.Constant;
import real.prop.vertical.TupleAssembler.UserModelAssembler;
import real.prop.vertical.Tuples.User.Users;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(value = Constant.USER_CONTROLLER)
public class UsersController {

    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final UserModelAssembler userModelAssembler;

    public UsersController(UserRepository userRepository, UserModelAssembler userModelAssembler) {
        this.userRepository = userRepository;
        this.userModelAssembler = userModelAssembler;
    }


    @GetMapping(value = Constant.USER_GET_ALL, produces = Constant.PRODUCE)
    @CrossOrigin
    public CollectionModel<EntityModel<Users>> readAll() {
        List<EntityModel<Users>> users = this.userRepository.findAll().stream().map(this.userModelAssembler::toModel)
                .collect(Collectors.toList());
        return new CollectionModel<>(users, linkTo(methodOn(UsersController.class).readAll()).withSelfRel());

    }


    @CrossOrigin
    @GetMapping(value = Constant.USER_GET_WITH_ID, produces = Constant.PRODUCE)
    public EntityModel<Users> readOne(@PathVariable String id) {
        if(this.userRepository.findById(id).isPresent()) {
            return this.userModelAssembler.toModel(this.userRepository.findById(id).get());
        }else {
            return null;
        }
    }

    @CrossOrigin
    @PostMapping(value = Constant.USER_SAVE, consumes = Constant.CONSUMES)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> create(Users newUser) {
        if (!this.userRepository.isUserExist(newUser.getUsername(), newUser.getEmail(), newUser.getPhonenumber())) {
            EntityModel<Users> entityModel = null;
            BCryptPasswordEncoder hasPasswordProvider = new BCryptPasswordEncoder();
            newUser.setPassword(hasPasswordProvider.encode(newUser.getPassword()));
            newUser.setLastUpdate(Constant.getCurrentDateAsString());
            newUser.setRegisterDate(Constant.getCurrentDateAsString());
            entityModel = this.userModelAssembler.toModel(this.userRepository.save(newUser));
            return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
        }else {
            return null;
        }
    }


    @PatchMapping(path = Constant.USER_UPDATE_WITH_ID, consumes = Constant.CONSUMES, produces = Constant.PRODUCE)
    @ResponseStatus(code = HttpStatus.OK)
    @CrossOrigin
    public Object update(@PathVariable String id, @RequestBody Users users) {
        if (this.userRepository.findById(id).isPresent()){
            Users existingUser = this.userRepository.findById(id).get();
            existingUser.setUsername(users.getUsername());
            existingUser.setPhonenumber(users.getPhonenumber());
            existingUser.setEmail(users.getEmail());
            existingUser.setPassword(users.getPassword());
            existingUser.setLastUpdate(Constant.getCurrentDateAsString());
            EntityModel<Users> entityModel = this.userModelAssembler.toModel(this.userRepository.save(existingUser));
            return ResponseEntity.created(userModelAssembler.toModel(this.userRepository.save(existingUser)).getRequiredLink(IanaLinkRelations.SELF)
            .toUri()).body(entityModel);

        }else {
            return null;
        }
    }


    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @DeleteMapping(value = Constant.USER_DELETE_WITH_ID)
    @CrossOrigin
    public void delete(@PathVariable String id) {
        if(this.userRepository.findById(id).isPresent()) {
            this.userRepository.delete(this.userRepository.findById(id).get());

        }
    }
}
