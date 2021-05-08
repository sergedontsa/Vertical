package real.prop.vertical.Controllers.Billing;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import real.prop.vertical.Exception.ResourceNotFoundException;
import real.prop.vertical.Repository.Apartment.ApartmentRepository;
import real.prop.vertical.Repository.Billing.BillingRepository;
import real.prop.vertical.Repository.Building.BuildingRepository;
import real.prop.vertical.Repository.Tenant.TenantRepository;
import real.prop.vertical.Resources.Constant;
import real.prop.vertical.Resources.IDGenerator;
import real.prop.vertical.TupleAssembler.BillingModelAssembler;
import real.prop.vertical.Tuples.Billing.Billing;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(value = Constant.BILLING_CONTROLLER)
public class BillingController {
    @Autowired
    private final BillingRepository billingRepository;
    @Autowired
    private final BillingModelAssembler billingModelAssembler;
    @Autowired
    private final TenantRepository tenantRepository;
    @Autowired
    private final ApartmentRepository apartmentRepository;
    @Autowired
    private final BuildingRepository buildingRepository;

    public BillingController(BillingRepository billingRepository, BillingModelAssembler billingModelAssembler, TenantRepository tenantRepository, ApartmentRepository apartmentRepository, BuildingRepository buildingRepository) {
        this.billingRepository = billingRepository;
        this.billingModelAssembler = billingModelAssembler;
        this.tenantRepository = tenantRepository;
        this.apartmentRepository = apartmentRepository;
        this.buildingRepository = buildingRepository;
    }

    @CrossOrigin
    @GetMapping(value = Constant.BILLING_GET_ALL, produces = Constant.PRODUCE)
    public CollectionModel<EntityModel<Billing>> readAll() {
        List<EntityModel<Billing>> billings = this.billingRepository.findAll().stream().map(this.billingModelAssembler::toModel)
                .collect(Collectors.toList());
        return new CollectionModel<>(billings, linkTo(methodOn(BillingController.class).readAll()).withSelfRel());
    }

    @CrossOrigin
    @GetMapping(value = Constant.BILLING_BY_TENANT_ID, produces = Constant.PRODUCE)
    public CollectionModel<EntityModel<Billing>> readByTenantId(@PathVariable String tenantid){
        List<EntityModel<Billing>> entityModels = this.billingRepository.findByTenantId(tenantid).stream().map(this.billingModelAssembler::toModel)
                .collect(Collectors.toList());
        return new CollectionModel<>(entityModels, linkTo(methodOn(BillingController.class).readByTenantId(tenantid)).withSelfRel());

    }

    @CrossOrigin
    @GetMapping(value = Constant.BILLING_GET_WITH_ID, produces = Constant.PRODUCE)
    public EntityModel<Billing> readOne(@PathVariable String id) {
        if (!this.billingRepository.existsById(id)){
            throw new ResourceNotFoundException("Billing id: " + id + " could not be found");
        }else{
            return this.billingModelAssembler.toModel(this.billingRepository.getOne(id));
        }
    }

    @PostMapping(value = Constant.BILLING_SAVE, consumes = Constant.CONSUMES)
    @ResponseStatus(HttpStatus.CREATED)
    @CrossOrigin
    public ResponseEntity<EntityModel<Billing>> create( @PathVariable String tenantId, @PathVariable String apartmentId, @PathVariable String buildingId, @RequestBody Billing object) {

        if (!this.tenantRepository.findByIdAndApartmentIdAndBuildingId(tenantId, apartmentId, buildingId).isPresent()){
            throw new ResourceNotFoundException("Error resource could not be found check IDs");
        }
        if (!tenantRepository.existsById(tenantId) ){
            throw new ResourceNotFoundException("TENANT ID: " + tenantId+ " could not be found");
        }
        if (!apartmentRepository.existsById(apartmentId)){
            throw new ResourceNotFoundException("APARTMENT ID: " + apartmentId+ " could not be found");
        }
        if (!buildingRepository.existsById(buildingId)){
            throw new ResourceNotFoundException("BUILDING ID: " + buildingId+ " could not be found");
        }
        object.setBillingid(IDGenerator.RECORD_ID());
        object.setTenant(this.tenantRepository.getOne(tenantId));
        object.setBuilding(this.buildingRepository.getOne(buildingId));
        object.setApartmentid(apartmentId);


        EntityModel<Billing> entityModel = billingModelAssembler.toModel(this.billingRepository.save(object));
        return ResponseEntity.created(billingModelAssembler.toModel(this.billingRepository.save(object))
                .getRequiredLink(IanaLinkRelations.SELF)
                .toUri()).body(entityModel);


    }

    public Object update(String id, String tenantId, Billing object) {
        if (!this.billingRepository.existsById(id)){
            throw new ResourceNotFoundException("BILLING ID: " + id+ " could not be found");
        }else {
            Billing existing = billingRepository.getOne(id);

        }
        return null;
    }

    @DeleteMapping(value = Constant.BILLING_DELETE_WITH_ID)
    @CrossOrigin
    public ResponseEntity<?> delete(@PathVariable String id, @PathVariable String tenantid) {
        return billingRepository.findByIdAndTenantId(tenantid, id).map(billing -> {
            billingRepository.delete(billing);
            return ResponseEntity.ok().build();
        }).orElseThrow(()-> new ResourceNotFoundException("BILLING ID:  " + id + " or TENANT ID: "+ tenantid  +" could not be found"));

    }
}
