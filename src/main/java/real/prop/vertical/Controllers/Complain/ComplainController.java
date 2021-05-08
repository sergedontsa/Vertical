package real.prop.vertical.Controllers.Complain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import real.prop.vertical.Repository.Complain.ComplainRepository;
import real.prop.vertical.Resources.Constant;
import real.prop.vertical.Resources.IDGenerator;
import real.prop.vertical.TupleAssembler.ComplainModelAssembler;
import real.prop.vertical.Tuples.Complain.Complain;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(value = Constant.COMPLAIN_CONTROLLER)
public class ComplainController {

    @Autowired
    private final ComplainRepository complainRepository;
    @Autowired
    private final ComplainModelAssembler complainModelAssembler;

    public ComplainController(ComplainRepository complainRepository, ComplainModelAssembler complainModelAssembler) {
        this.complainRepository = complainRepository;
        this.complainModelAssembler = complainModelAssembler;
    }


    @GetMapping(value = Constant.COMPLAIN_GET_ALL, produces = Constant.PRODUCE)
    @CrossOrigin
    public CollectionModel<EntityModel<Complain>> readAll() {
         List<EntityModel<Complain>> complains = this.complainRepository
                 .findAll()
                 .stream()
                 .map(this.complainModelAssembler::toModel)
                 .collect(Collectors.toList());
        return new CollectionModel<>(complains, linkTo(methodOn(ComplainController.class).readAll()).withSelfRel());
    }


    @GetMapping(value = Constant.COMPLAIN_GET_WITH_ID, produces = Constant.PRODUCE)
    @CrossOrigin
    public EntityModel<Complain> readOne(@PathVariable String id) {
        if (this.complainRepository.findById(id).isPresent()){
            return this.complainModelAssembler.toModel(this.complainRepository.findById(id).get());
        }else {
            return null;
        }
    }

    @PostMapping(value = Constant.COMPLAIN_SAVE, consumes = Constant.CONSUMES)
    @ResponseStatus(HttpStatus.CREATED)
    @CrossOrigin
    public ResponseEntity<?> create(@RequestBody Complain complain) {

        complain.setStatus("Under Review");
        complain.setComplainid(IDGenerator.COMPLAIN_ID());
        EntityModel<Complain> entityModel = this.complainModelAssembler.toModel(this.complainRepository.save(complain));
        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);

    }

    @PatchMapping(path = Constant.COMPLAIN_UPDATE_WITH_ID, consumes = Constant.CONSUMES)
    @ResponseStatus(code = HttpStatus.OK)
    @CrossOrigin
    public Object update(@PathVariable String id, @RequestBody Complain complain) {
        if (this.complainRepository.findById(id).isPresent()){
            Complain existingComplain = this.complainRepository.findById(id).get();
            existingComplain.setPersonid(complain.getPersonid());
            existingComplain.setType(complain.getType());
            existingComplain.setTitle(complain.getTitle());
            existingComplain.setBody(complain.getBody());
            existingComplain.setPhonenumber(complain.getPhonenumber());
            existingComplain.setSeverity(complain.getSeverity());
            existingComplain.setAddress(complain.getAddress());
            if (complain.getStatus() != null){
                existingComplain.setStatus(complain.getStatus());
            }else {
                existingComplain.setStatus("Pending");
            }
            existingComplain.setAssignto(complain.getAssignto());


            EntityModel<Complain> entityModel = this.complainModelAssembler
                    .toModel(this.complainRepository.save(existingComplain));

            return ResponseEntity.created(entityModel
                    .getRequiredLink(IanaLinkRelations.SELF)
                    .toUri())
                    .body(entityModel);



        }else {
            return null;
        }
    }


    @DeleteMapping(value = Constant.COMPLAIN_DELETE_WITH_ID)
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @CrossOrigin
    public void delete(@PathVariable String id) {
        if (this.complainRepository.findById(id).isPresent()){
            this.complainRepository.delete(this.complainRepository.findById(id).get());
        }
    }
}

