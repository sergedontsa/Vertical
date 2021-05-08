package real.prop.vertical.TupleAssembler;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import real.prop.vertical.Controllers.Building.BuildingAddressController;
import real.prop.vertical.Tuples.Building.BuildingAddress;

import javax.validation.constraints.NotNull;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class BuildingAddressModelAssembler implements RepresentationModelAssembler<BuildingAddress, EntityModel<BuildingAddress>> {

    @Override
    public   EntityModel<BuildingAddress> toModel(BuildingAddress entity) {
        return new EntityModel<>(entity,
                linkTo(methodOn(BuildingAddressController.class).readOne(entity.getBuildingid())).withSelfRel(),
                linkTo(methodOn(BuildingAddressController.class).readAll()).withRel("buildingAddress"));
    }

    @Override
    public @NotNull CollectionModel<EntityModel<BuildingAddress>> toCollectionModel(@NotNull Iterable<? extends BuildingAddress> entities) {
        return null;
    }
}
