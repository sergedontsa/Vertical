package real.prop.vertical.TupleAssembler.Tenant;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import real.prop.vertical.Controllers.Tenant.TenantController;
import real.prop.vertical.Tuples.Tenant.Tenant;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class TenantModelAssembler implements RepresentationModelAssembler<Tenant, EntityModel<Tenant>> {



    @Override
    public EntityModel<Tenant> toModel(Tenant entity) {
        return new EntityModel<>(entity,
                linkTo(methodOn(TenantController.class).readOne(entity.getTenantid())).withSelfRel(),
                linkTo(methodOn(TenantController.class).readAll()).withRel("Tenants"));

    }

    @Override
    public CollectionModel<EntityModel<Tenant>> toCollectionModel(Iterable<? extends Tenant> entities) {
        return null;
    }
}
