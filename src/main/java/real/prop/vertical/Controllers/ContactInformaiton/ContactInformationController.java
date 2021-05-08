package real.prop.vertical.Controllers.ContactInformaiton;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import real.prop.vertical.Repository.ContactInformation.ContactInformationRepository;
import real.prop.vertical.Resources.Constant;
import real.prop.vertical.TupleAssembler.ContactInformation.ContactInformationAssembler;
import real.prop.vertical.Tuples.ContactInformation.ContactInformation;


@RestController
@RequestMapping(value = Constant.CONTACT_INFORMATION_CONTROLLER)
public class ContactInformationController {

    @Autowired
    private final ContactInformationAssembler contactInformationAssembler;
    @Autowired
    private final ContactInformationRepository contactInformationRepository;

    public ContactInformationController(ContactInformationRepository contactInformationRepository, ContactInformationAssembler contactInformationAssembler) {
        this.contactInformationAssembler = contactInformationAssembler;
        this.contactInformationRepository = contactInformationRepository;
    }

    @CrossOrigin
    @GetMapping(value = Constant.CONTACT_INFORMATION_GET_ALL, produces = Constant.PRODUCE)
    public CollectionModel<EntityModel<ContactInformation>> readAll(){
        return null;
    }
    @CrossOrigin
    @GetMapping(value = Constant.CONTACT_INFORMATION_GET_ONE)
    public EntityModel<ContactInformation> readOne(@PathVariable String phoneNumber){
        return null;
    }
    @CrossOrigin
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = Constant.CONTACT_INFORMATION_SAVE, consumes = Constant.CONSUMES)
    public ResponseEntity<?> create(@PathVariable String phoneNumber, @RequestBody ContactInformation contactInformation){
        return null;
    }
    @CrossOrigin
    @DeleteMapping(value = Constant.CONTACT_INFORMATION_DELETE, produces = Constant.PRODUCE)
    public ResponseEntity<?> delete(@PathVariable String phoneNumber){
        return null;
    }
    @CrossOrigin
    @ResponseStatus(code = HttpStatus.OK)
    @PatchMapping(path = Constant.CONTACT_INFORMATION_UPDATE, consumes = Constant.CONSUMES)
    public ResponseEntity<?> update(@PathVariable String phoneNumber, @RequestBody ContactInformation contactInformation){
        return null;
    }

}
