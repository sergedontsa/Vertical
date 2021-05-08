package real.prop.vertical.TupleAssembler.Apartment;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import real.prop.vertical.Controllers.Apartment.ApartmentDimensionController;
import real.prop.vertical.Tuples.Apartment.ApartmentDimension;

import javax.validation.constraints.NotNull;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ApartmentDimensionAssembler implements RepresentationModelAssembler<ApartmentDimension, EntityModel<ApartmentDimension>> {
    @Override
    public @NotNull EntityModel<ApartmentDimension> toModel(@NotNull ApartmentDimension entity) {
        return new EntityModel<>(entity,
                linkTo(methodOn(ApartmentDimensionController.class).readOne(entity.getApartmentId())).withSelfRel(),
                linkTo(methodOn(ApartmentDimensionController.class).readAll()).withRel("dimension"));
    }

    @Override
    public CollectionModel<EntityModel<ApartmentDimension>> toCollectionModel(Iterable<? extends ApartmentDimension> entities) {
        return null;
    }
}
