package real.prop.vertical.TupleAssembler;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import real.prop.vertical.Controllers.SubscriberController;
import real.prop.vertical.Tuples.User.Subscriber;

import javax.validation.constraints.NotNull;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class SubscriberModelAssembler implements RepresentationModelAssembler<Subscriber, EntityModel<Subscriber>> {
    @Override
    public  EntityModel<Subscriber> toModel(Subscriber entity) {
        return new EntityModel<>(entity,
                linkTo(methodOn(SubscriberController.class).readOne(entity.getId())).withSelfRel(),
                linkTo(methodOn(SubscriberController.class).readAll()).withRel("subscriber"));
    }

    @Override
    public @NotNull CollectionModel<EntityModel<Subscriber>> toCollectionModel(@NotNull Iterable<? extends Subscriber> entities) {
        return null;
    }
}
