package real.prop.vertical.TupleAssembler.Tenant;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import real.prop.vertical.Controllers.Tenant.TenantSubController;
import real.prop.vertical.Tuples.Tenant.TenantSub;

import javax.validation.constraints.NotNull;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class TenantSubAssembler implements RepresentationModelAssembler<TenantSub, EntityModel<TenantSub>> {
    @Override
    public @NotNull EntityModel<TenantSub> toModel(@NotNull TenantSub entity) {
        return new EntityModel<>(entity,
                linkTo(methodOn(TenantSubController.class).readOne(entity.getTenantid())).withSelfRel(),
                linkTo(methodOn(TenantSubController.class).readAll()).withRel("Sub_Tenant"));
    }

    @Override
    public CollectionModel<EntityModel<TenantSub>> toCollectionModel(Iterable<? extends TenantSub> entities) {
        return null;
    }
}
