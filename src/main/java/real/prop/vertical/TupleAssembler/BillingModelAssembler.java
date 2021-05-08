package real.prop.vertical.TupleAssembler;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import real.prop.vertical.Controllers.Billing.BillingController;
import real.prop.vertical.Tuples.Billing.Billing;

import javax.validation.constraints.NotNull;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class BillingModelAssembler implements RepresentationModelAssembler<Billing, EntityModel<Billing>> {
    @Override
    public @NotNull EntityModel<Billing> toModel(@NotNull Billing entity) {
        return new EntityModel<>(entity,
                linkTo(methodOn(BillingController.class).readOne(entity.getBillingid())).withSelfRel(),
                linkTo(methodOn(BillingController.class).readAll()).withRel("billing")
                );
    }

    @Override
    public @NotNull CollectionModel<EntityModel<Billing>> toCollectionModel(@NotNull Iterable<? extends Billing> entities) {
        return null;
    }
}
