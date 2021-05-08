package real.prop.vertical.TupleAssembler.Apartment;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import real.prop.vertical.Controllers.Apartment.ApartmentController;
import real.prop.vertical.Tuples.Apartment.Apartment;

import javax.validation.constraints.NotNull;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@Component
public class ApartmentModelAssembler implements RepresentationModelAssembler<Apartment, EntityModel<Apartment>>
{

    @Override
    public @NotNull EntityModel<Apartment> toModel(@NotNull Apartment entity) {
        return new EntityModel<>(entity,
                linkTo(methodOn(ApartmentController.class).readOne(entity.getApartmentID())).withSelfRel(),
                linkTo(methodOn(ApartmentController.class).readAll()).withRel("apartments"));
    }

    @Override
    public @NotNull CollectionModel<EntityModel<Apartment>> toCollectionModel(@NotNull Iterable<? extends Apartment> entities) {
        return null;
    }


}
