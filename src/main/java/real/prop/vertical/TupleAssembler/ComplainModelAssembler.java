package real.prop.vertical.TupleAssembler;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import real.prop.vertical.Controllers.Complain.ComplainController;
import real.prop.vertical.Tuples.Complain.Complain;

import javax.validation.constraints.NotNull;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ComplainModelAssembler implements RepresentationModelAssembler<Complain, EntityModel<Complain>> {
    @Override
    public   EntityModel<Complain> toModel( Complain entity) {
        return new EntityModel<>(entity,
                linkTo(methodOn(ComplainController.class).readOne(entity.getComplainid())).withSelfRel(),
                linkTo(methodOn(ComplainController.class).readAll()).withRel("Complain"));
    }

    @Override
    public @NotNull CollectionModel<EntityModel<Complain>> toCollectionModel(@NotNull Iterable<? extends Complain> entities) {
        return null;
    }
}
