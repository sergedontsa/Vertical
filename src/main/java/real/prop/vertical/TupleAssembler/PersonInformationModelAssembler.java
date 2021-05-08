package real.prop.vertical.TupleAssembler;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import real.prop.vertical.Controllers.PersonInformationController;
import real.prop.vertical.Tuples.Personinformation;

import javax.validation.constraints.NotNull;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class PersonInformationModelAssembler implements RepresentationModelAssembler<Personinformation, EntityModel<Personinformation>> {
    @Override
    public  EntityModel<Personinformation> toModel( Personinformation personinformation) {
        return new EntityModel<>(personinformation,
                linkTo(methodOn(PersonInformationController.class).readOne(personinformation.getDataid())).withSelfRel(),
                linkTo(methodOn(PersonInformationController.class).readAll()).withRel("Record"));
    }

    @Override
    public @NotNull CollectionModel<EntityModel<Personinformation>> toCollectionModel(@NotNull Iterable<? extends Personinformation> entities) {
        return null;
    }
}
