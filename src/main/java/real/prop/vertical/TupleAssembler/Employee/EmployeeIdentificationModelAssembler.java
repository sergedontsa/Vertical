package real.prop.vertical.TupleAssembler.Employee;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import real.prop.vertical.Controllers.Employee.EmployeeIdentificationController;
import real.prop.vertical.Tuples.Employee.EmployeeIdentification;

import javax.validation.constraints.NotNull;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class EmployeeIdentificationModelAssembler implements RepresentationModelAssembler<EmployeeIdentification, EntityModel<EmployeeIdentification>> {
    @Override
    public @NotNull EntityModel<EmployeeIdentification> toModel(@NotNull EmployeeIdentification entity) {
        return new EntityModel<>(entity,
                linkTo(methodOn(EmployeeIdentificationController.class).readOne(entity.getEmployeeId())).withSelfRel(),
                linkTo(methodOn(EmployeeIdentificationController.class).readAll()).withRel("Identification")
                );
    }

    @Override
    public @NotNull CollectionModel<EntityModel<EmployeeIdentification>> toCollectionModel(@NotNull Iterable<? extends EmployeeIdentification> entities) {
        return null;
    }
}
