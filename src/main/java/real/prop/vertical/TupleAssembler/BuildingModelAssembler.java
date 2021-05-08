package real.prop.vertical.TupleAssembler;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import real.prop.vertical.Controllers.Building.BuildingController;
import real.prop.vertical.Tuples.Building.Building;

import javax.validation.constraints.NotNull;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class BuildingModelAssembler implements RepresentationModelAssembler<Building, EntityModel<Building>> {
    @Override
    public  EntityModel<Building> toModel( Building building) {
        return new EntityModel<>(building,
                linkTo(methodOn(BuildingController.class).readOne(building.getBuildingId())).withSelfRel(),
                linkTo(methodOn(BuildingController.class).readAll()).withRel("buildings")
                );
    }

    @Override
    public @NotNull CollectionModel<EntityModel<Building>> toCollectionModel(@NotNull Iterable<? extends Building> entities) {
        return null;
    }
}
