package real.prop.vertical.TupleAssembler.ContactInformation;


import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import real.prop.vertical.Controllers.ContactInformaiton.ContactInformationController;
import real.prop.vertical.Tuples.ContactInformation.ContactInformation;

import javax.validation.constraints.NotNull;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ContactInformationAssembler implements RepresentationModelAssembler<ContactInformation, EntityModel<ContactInformation>> {
    @Override
    public @NotNull EntityModel<ContactInformation> toModel(@NotNull ContactInformation entity) {
        return new EntityModel<>(entity,
                linkTo(methodOn(ContactInformationController.class).readOne(entity.getPhonenumber())).withSelfRel(),
                linkTo(methodOn(ContactInformationController.class).readAll()).withRel("Contact"));
    }

    @Override
    public CollectionModel<EntityModel<ContactInformation>> toCollectionModel(Iterable<? extends ContactInformation> entities) {
        return null;
    }
}
