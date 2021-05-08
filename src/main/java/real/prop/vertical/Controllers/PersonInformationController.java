package real.prop.vertical.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import real.prop.vertical.Repository.PersonInformation.PersonInformationRepository;
import real.prop.vertical.Resources.Constant;
import real.prop.vertical.Resources.IDGenerator;
import real.prop.vertical.TupleAssembler.PersonInformationModelAssembler;
import real.prop.vertical.Tuples.Personinformation;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(value = Constant.PERSONAL_INFORMATION_CONTROLLER)
public class PersonInformationController {

    @Autowired
    private final PersonInformationRepository personInformationRepository;
    @Autowired
    private final PersonInformationModelAssembler personInformationModelAssembler;

    public PersonInformationController(PersonInformationRepository personInformationRepository, PersonInformationModelAssembler personInformationModelAssembler) {
        this.personInformationRepository = personInformationRepository;
        this.personInformationModelAssembler = personInformationModelAssembler;
    }

    /**
     * This method return the collection of all entity
     * @return the collection
     */
    @GetMapping(value = Constant.PERSONAL_INFORMATION_GET_ALL, produces = Constant.PRODUCE)
    @CrossOrigin
    public CollectionModel<EntityModel<Personinformation>> readAll(){
       List<EntityModel<Personinformation>> personInformations = this.personInformationRepository.findAll().stream()
               .map(this.personInformationModelAssembler::toModel)
               .collect(Collectors.toList());
       return new CollectionModel<>(personInformations, linkTo(methodOn(PersonInformationController.class).readAll()).withSelfRel());
    }
    /**
     * Return the entity with id if found in the  server
     * @param id the id of the entity
     * @return the entity
     */
    @GetMapping(value = Constant.PERSONAL_INFORMATION_GET_WITH_ID, produces = Constant.PRODUCE)
    @CrossOrigin
    public EntityModel<Personinformation> readOne(@PathVariable String id) {
        if (this.personInformationRepository.findById(id).isPresent()){
            return this.personInformationModelAssembler.toModel(this.personInformationRepository.findById(id).get());
        }else {
            return null;
        }
    }

    /**
     * In case the entity is referred with the id with type integer
     * Return the entity with id if found in the  server
     *
     * @param id the id of the entity
     * @return the entity
     */

    public EntityModel<Personinformation> readOne(int id) {
        return null;
    }


    /**
     * Delete the value in the database
     * @param id the id of the entity to updated
     */

    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @DeleteMapping(value = Constant.PERSONAL_INFORMATION_DELETE_WITH_ID)
    @CrossOrigin
    public void delete(@PathVariable String id){
        if (this.personInformationRepository.findById(id).isPresent()){
            this.personInformationRepository.delete(this.personInformationRepository.findById(id).get());
        }
    }


    /**
     * Update the entity in the server
     * @param id the id of the entity
     * @param personinformation the updated data
     * @return the updated one
     */

    @PatchMapping(path = Constant.PERSONAL_INFORMATION_UPDATE_WITH_ID, consumes = Constant.CONSUMES, produces = Constant.PRODUCE)
    @ResponseStatus(code = HttpStatus.OK)
    @CrossOrigin
    public Object update(@PathVariable String id, @RequestBody Personinformation personinformation){
        if (this.personInformationRepository.findById(id).isPresent()){
            Personinformation existingRecord = this.personInformationRepository.findById(id).get();
            existingRecord.setPersonid(personinformation.getPersonid());
            existingRecord.setPersonstatus(personinformation.getPersonstatus());
            existingRecord.setPhonenumber(personinformation.getPhonenumber());
            existingRecord.setEmail(personinformation.getEmail());
            existingRecord.setIdtype(personinformation.getIdtype());
            existingRecord.setIdnumber(personinformation.getIdnumber());
            existingRecord.setIdissuedate(personinformation.getIdissuedate());
            existingRecord.setIdexpireddate(personinformation.getIdexpireddate());
            existingRecord.setAddress(personinformation.getAddress());
            existingRecord.setCountry(personinformation.getCountry());

            EntityModel<Personinformation> entityModel = this.personInformationModelAssembler.toModel(this.personInformationRepository.save(existingRecord));
            return ResponseEntity.created(this.personInformationModelAssembler.toModel(this.personInformationRepository.save(existingRecord))
            .getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
        }else {
            return HttpStatus.NOT_FOUND;
        }
    }

    /**
     * Create the new entity in server
     * @param personinformation the entity
     * @return the create entity
     */

    @PostMapping(value = Constant.PERSONAL_INFORMATION_SAVE, consumes = Constant.CONSUMES)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> create(@org.jetbrains.annotations.NotNull @RequestBody Personinformation personinformation){
        personinformation.setDataid(IDGenerator.RECORD_ID());
        EntityModel<Personinformation> entityModel = this.personInformationModelAssembler.toModel(this.personInformationRepository.save(personinformation));
        return ResponseEntity.created(this.personInformationModelAssembler.toModel(this.personInformationRepository.save(personinformation))
        .getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);

    }
}
