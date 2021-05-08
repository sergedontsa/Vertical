package real.prop.vertical.Controllers.Apartment;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import real.prop.vertical.Enum.ApartmentStatus;
import real.prop.vertical.Exception.ResourceNotFoundException;
import real.prop.vertical.Repository.Apartment.ApartmentDimensionRepository;
import real.prop.vertical.Repository.Apartment.ApartmentFeeRepository;
import real.prop.vertical.Repository.Apartment.ApartmentRepository;
import real.prop.vertical.Repository.Building.BuildingRepository;
import real.prop.vertical.Resources.Constant;
import real.prop.vertical.Resources.IDGenerator;
import real.prop.vertical.TupleAssembler.Apartment.ApartmentModelAssembler;
import real.prop.vertical.Tuples.Apartment.Apartment;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * Note: still check delete request
 * the request work but does not delete the entity
 */

@RestController
@RequestMapping(value = Constant.APARTMENT_CONTROLLER)
//@PreAuthorize("isAuthenticated()")
public class ApartmentController{
    private static final Logger logger = LoggerFactory.getLogger(ApartmentController.class);
    @Autowired
    private final ApartmentRepository apartmentTupleRepository;
    @Autowired
    private final ApartmentModelAssembler apartmentModelAssembler;
    @Autowired
    private final BuildingRepository buildingRepository;
    @Autowired
    private final ApartmentDimensionRepository apartmentDimensionRepository;
    @Autowired
    private final ApartmentFeeRepository apartmentFeeRepository;

    /**
     * Controller
     * @param apartmentTupleRepository apartment repository
     * @param apartmentModelAssembler assembler
     * @param buildingRepository building repository
     * @param apartmentDimensionRepository dimension repository
     * @param apartmentFeeRepository fee repository
     */
    public ApartmentController(ApartmentRepository apartmentTupleRepository, ApartmentModelAssembler apartmentModelAssembler, BuildingRepository buildingRepository, ApartmentDimensionRepository apartmentDimensionRepository, ApartmentFeeRepository apartmentFeeRepository) {
        this.apartmentTupleRepository = apartmentTupleRepository;
        this.apartmentModelAssembler = apartmentModelAssembler;
        this.buildingRepository = buildingRepository;
        this.apartmentDimensionRepository = apartmentDimensionRepository;
        this.apartmentFeeRepository = apartmentFeeRepository;
    }

    /**
     * Read all apartment
     * @return all apartment
     */
    @GetMapping(value = Constant.APARTMENT_GET_ALL, produces = Constant.PRODUCE)
    @CrossOrigin
    //@PreAuthorize("permitAll()")
    public CollectionModel<EntityModel<Apartment>> readAll(){
        logger.info("Read All: " + Constant.APARTMENT_GET_ALL);
        List<EntityModel<Apartment>> apartments = this.apartmentTupleRepository
                .findAll()
                .stream()
                .sorted()
                .map(this.apartmentModelAssembler::toModel).collect(Collectors.toList());
        return new CollectionModel<>(apartments, linkTo(methodOn(ApartmentController.class)
                .readAll())
                .withSelfRel());
    }

    /**
     * Read one Apartment
     * @param id id
     * @return the apartment
     */
    @GetMapping(value = Constant.APARTMENT_GET_WITH_ID, produces = Constant.PRODUCE)
    @CrossOrigin
    public EntityModel<Apartment> readOne(@PathVariable String id){
        logger.info("Read One: " + id);
            if (this.apartmentTupleRepository.findById(id).isPresent()) {
                return this.apartmentModelAssembler.toModel(this.apartmentTupleRepository.findById(id).get());
            } else {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            }

    }

    /**
     * Save one Apartment
     * @param buildingId building id
     * @param apartment apartment
     * @return the saved entity
     */
    @PostMapping(value = Constant.APARTMENT_SAVE, consumes = Constant.CONSUMES)
    @ResponseStatus(HttpStatus.CREATED)
    @CrossOrigin
    public ResponseEntity<?> create(@PathVariable String buildingId,@RequestBody Apartment apartment){
        logger.info("[Create]: Building Id" + buildingId);
        logger.info("[Object]: " + apartment);
        if (!this.buildingRepository.existsById(buildingId)){
            throw new ResourceNotFoundException("BUILDING ID: " + buildingId+ " could not be found");
        }else {
            apartment.setApartmentID(IDGenerator.APARTMENT_ID());

            apartment.setStatus(String.valueOf(ApartmentStatus.AVAILABLE));

            apartment.setStatus(String.valueOf(ApartmentStatus.AVAILABLE));


            apartment.setBuilding(this.buildingRepository.getOne(buildingId));
            EntityModel<Apartment> entityModel = this.apartmentModelAssembler
                    .toModel(this.apartmentTupleRepository.save(apartment));

            return ResponseEntity.created(apartmentModelAssembler.toModel(this.apartmentTupleRepository.save(apartment))
                    .getRequiredLink(IanaLinkRelations.SELF)
                    .toUri()).body(entityModel);
        }
    }

    /**
     * Delete apartment
     * @param apartmentId id
     * @return response
     */
    @DeleteMapping(value = Constant.APARTMENT_DELETE_WITH_ID, produces = Constant.PRODUCE)
    @CrossOrigin
    public ResponseEntity<?> delete(@PathVariable String apartmentId){
        logger.warn("[DELETE]: ID" + apartmentId);
        if (!apartmentTupleRepository.existsById(apartmentId)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        this.apartmentTupleRepository.deleteById(apartmentId);
        this.apartmentDimensionRepository.deleteById(apartmentId);
        this.apartmentFeeRepository.deleteById(apartmentId);
        return new ResponseEntity<>(HttpStatus.OK);

    }

    /**
     * Update apartment
     * @param id id
     * @param buildingId building id
     * @param apartment apartment
     * @return return the response
     */
    @PatchMapping(path = Constant.APARTMENT_UPDATE_WITH_ID, consumes = Constant.CONSUMES)
    @ResponseStatus(code = HttpStatus.OK)
    @CrossOrigin
    public ResponseEntity<?> update(@PathVariable String id, @PathVariable String buildingId, @RequestBody Apartment apartment){
        logger.info("[UPDATE]: Apartment ID" + id );
        logger.info("[UPDATE]: Building ID" + buildingId );
        logger.info("[UPDATE]: Apartment" + apartment );
        if(!this.apartmentTupleRepository.existsById(id) || !this.buildingRepository.existsById(buildingId)){
            logger.error("Resource Id: " + id+ " could not be found");
            throw new ResourceNotFoundException("Resource Id: " + id+ " could not be found");
        }else {
            apartment.setBuilding(this.buildingRepository.getOne(buildingId));
            apartment.setApartmentFee(this.apartmentFeeRepository.getOne(id));
            apartment.setApartmentDimension(this.apartmentDimensionRepository.getOne(id));
            apartment.setApartmentID(id);

            this.apartmentTupleRepository.save(apartment);
            logger.info("[UPDATE]: Status" + HttpStatus.OK.toString());
            return new ResponseEntity<Apartment>(HttpStatus.OK);
        }

    }
}
