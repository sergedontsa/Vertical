package real.prop.vertical.TupleAssembler;


import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import real.prop.vertical.Controllers.User.UsersController;
import real.prop.vertical.Tuples.User.Users;

import javax.validation.constraints.NotNull;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class UserModelAssembler implements RepresentationModelAssembler<Users, EntityModel<Users>> {
    @Override
    public  EntityModel<Users> toModel( Users entity) {
        return new EntityModel<>(entity,
                linkTo(methodOn(UsersController.class).readOne(entity.getUserId())).withSelfRel(),
                linkTo(methodOn(UsersController.class).readAll()).withRel("users"));
    }

    @Override
    public @NotNull CollectionModel<EntityModel<Users>> toCollectionModel(@NotNull Iterable<? extends Users> entities) {
        return null;
    }


}
