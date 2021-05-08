package real.prop.vertical.TupleAssembler.Apartment;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import real.prop.vertical.Controllers.Apartment.ApartmentFeeController;
import real.prop.vertical.Tuples.Apartment.ApartmentFee;

import javax.validation.constraints.NotNull;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ApartmentFeeAssembler implements RepresentationModelAssembler<ApartmentFee, EntityModel<ApartmentFee>> {
    @Override
    public @NotNull EntityModel<ApartmentFee> toModel(@NotNull ApartmentFee entity) {
        return new EntityModel<>(entity,
                linkTo(methodOn(ApartmentFeeController.class).readOne(entity.getApartmentId())).withSelfRel(),
                linkTo(methodOn(ApartmentFeeController.class).readAll()).withRel("fees"));
    }

    @Override
    public CollectionModel<EntityModel<ApartmentFee>> toCollectionModel(Iterable<? extends ApartmentFee> entities) {
        return null;
    }
}
