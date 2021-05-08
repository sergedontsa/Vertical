package real.prop.vertical.TupleAssembler;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import real.prop.vertical.Controllers.Listening.ListeningController;
import real.prop.vertical.Tuples.Listening.Listening;

import javax.validation.constraints.NotNull;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ListeningAssembler implements RepresentationModelAssembler<Listening, EntityModel<Listening>> {
    @Override
    public  EntityModel<Listening> toModel( Listening entity) {
        return new EntityModel<>(entity,
                linkTo(methodOn(ListeningController.class).readOne(entity.getApartmentId())).withSelfRel(),
                linkTo(methodOn(ListeningController.class).readAll()).withRel("Listening")
                );
    }

    @Override
    public @NotNull CollectionModel<EntityModel<Listening>> toCollectionModel(@NotNull Iterable<? extends Listening> entities) {
        return null;
    }
}
