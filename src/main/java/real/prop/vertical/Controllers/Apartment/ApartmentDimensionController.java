package real.prop.vertical.Controllers.Apartment;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import real.prop.vertical.Exception.ResourceNotFoundException;
import real.prop.vertical.Repository.Apartment.ApartmentDimensionRepository;
import real.prop.vertical.Repository.Apartment.ApartmentRepository;
import real.prop.vertical.Resources.Constant;
import real.prop.vertical.TupleAssembler.Apartment.ApartmentDimensionAssembler;
import real.prop.vertical.Tuples.Apartment.ApartmentDimension;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * This class control the apartment dimension
 */
@RestController
@RequestMapping(value = Constant.APARTMENT_DIMENSION_CONTROLLER)
public class ApartmentDimensionController {

    @Autowired
    private final ApartmentDimensionRepository apartmentDimensionRepository;
    @Autowired
    private final ApartmentDimensionAssembler apartmentDimensionAssembler;
    @Autowired
    private final ApartmentRepository apartmentRepository;

    /**
     * The constructor
     * @param apartmentDimensionRepository dimension repository
     * @param apartmentDimensionAssembler dimension assembler
     * @param apartmentRepository apartment repository
     */
    public ApartmentDimensionController(ApartmentDimensionRepository apartmentDimensionRepository, ApartmentDimensionAssembler apartmentDimensionAssembler, ApartmentRepository apartmentRepository) {
        this.apartmentDimensionAssembler = apartmentDimensionAssembler;
        this.apartmentDimensionRepository = apartmentDimensionRepository;
        this.apartmentRepository = apartmentRepository;
    }

    /**
     * Read all dimension
     * @return all dimension
     */
    @GetMapping(value = Constant.APARTMENT_DIMENSION_GET_ALL, produces = Constant.PRODUCE)
    @CrossOrigin
    public CollectionModel<EntityModel<ApartmentDimension>> readAll(){
        List<EntityModel<ApartmentDimension>> dimension = this.apartmentDimensionRepository
                .findAll()
                .stream()
                .map(this.apartmentDimensionAssembler::toModel)
                .collect(Collectors.toList());

        return new CollectionModel<>(dimension,
                linkTo(methodOn(ApartmentDimensionController.class)
                .readAll())
        .withSelfRel());
    }

    /**
     * Read one dimension
     * @param apartmentId apartment id
     * @return dimension
     */
    @GetMapping(value = Constant.APARTMENT_DIMENSION_GET_WITH_APARTMENT_ID)
    @CrossOrigin
    public EntityModel<ApartmentDimension> readOne(@PathVariable String apartmentId){
        ApartmentDimension apartmentDimension = this.apartmentDimensionRepository.findById(apartmentId)
                .orElseThrow(()-> new ResourceNotFoundException("Resource Id: " + apartmentId + " could not be found"));

        return this.apartmentDimensionAssembler.toModel(apartmentDimension);
    }

    /**
     * Creat one
     * @param apartmentId apartment id
     * @param dimension dimension
     * @return return response
     */
    @PostMapping(value = Constant.APARTMENT_DIMENSION_SAVE_WITH_APARTMENT_ID, consumes = Constant.CONSUMES)
    @CrossOrigin
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> create(@PathVariable String apartmentId, @RequestBody ApartmentDimension dimension){
        if (!this.apartmentRepository.existsById(apartmentId)){
            throw new ResourceNotFoundException("Resource Id: " + apartmentId + " could not be found");
        }
        dimension.setApartmentId(apartmentId);
        EntityModel<ApartmentDimension> entityModel = this.apartmentDimensionAssembler.toModel(this.apartmentDimensionRepository.save(dimension));
        this.apartmentRepository.setApartmentDimensionId(apartmentId, apartmentId);
        return ResponseEntity.created(this.apartmentDimensionAssembler.toModel(this.apartmentDimensionRepository.save(dimension))
        .getRequiredLink(IanaLinkRelations.SELF)
        .toUri()).body(entityModel);
    }

    /**
     * Update dimension
     * @param apartmentId apartment id
     * @param apartmentDimension dimension
     * @return response
     */
    @PatchMapping(path = Constant.APARTMENT_DIMENSION_UPDATE_WITH_APARTMENT_ID, consumes = Constant.CONSUMES)
    @CrossOrigin
    public ResponseEntity<?> update(@PathVariable String apartmentId, @RequestBody ApartmentDimension apartmentDimension){
        if (!this.apartmentRepository.existsById(apartmentId) || !this.apartmentDimensionRepository.existsById(apartmentId)){
            throw new ResourceNotFoundException("Resource Id: " + apartmentId + " could not be found");
        }else {
            apartmentDimension.setApartmentId(apartmentId);
            this.apartmentDimensionRepository.save(apartmentDimension);
            return new ResponseEntity<ApartmentDimension>(HttpStatus.OK);
        }
    }
}
