package real.prop.vertical.Controllers.Listening;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;
import real.prop.vertical.Repository.Listenening.ListeningRepository;
import real.prop.vertical.Resources.Constant;
import real.prop.vertical.TupleAssembler.ListeningAssembler;
import real.prop.vertical.Tuples.Listening.Listening;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(value = Constant.LISTENING_CONTROLLER)
public class ListeningController {

    @Autowired
    private final ListeningRepository listeningRepository;
    @Autowired
    private final ListeningAssembler listeningAssembler;

    public ListeningController(ListeningRepository listeningRepository, ListeningAssembler listeningAssembler) {
        this.listeningRepository = listeningRepository;
        this.listeningAssembler = listeningAssembler;
    }


    @CrossOrigin
    @GetMapping(value = Constant.LISTENING_GET_ALL, produces = Constant.PRODUCE)
    public CollectionModel<EntityModel<Listening>> readAll() {
        List<EntityModel<Listening>> listening = this.listeningRepository.findAll().stream()
                .map(this.listeningAssembler::toModel).collect(Collectors.toList());

        return new CollectionModel<>(listening, linkTo(methodOn(ListeningController.class).readAll()).withSelfRel());

    }

    @CrossOrigin
    @GetMapping(value = Constant.LISTENING_GET_WITH_ID, produces = Constant.PRODUCE)
    public EntityModel<Listening> readOne(@PathVariable String id) {
        if (this.listeningRepository.findById(id).isPresent()){
            return this.listeningAssembler.toModel(this.listeningRepository.findById(id).get());
        }else {
            return null;
        }
    }


}
