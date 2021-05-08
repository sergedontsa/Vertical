package real.prop.vertical.TupleAssembler.Employee;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import real.prop.vertical.Controllers.Employee.EmployeeAddressController;
import real.prop.vertical.Tuples.Employee.EmployeeAddress;

import javax.validation.constraints.NotNull;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class EmployeeAddressModelAssembler implements RepresentationModelAssembler<EmployeeAddress, EntityModel<EmployeeAddress>>{

    @Override
    public @NotNull EntityModel<EmployeeAddress> toModel(EmployeeAddress entity) {
        return new EntityModel<>(entity,
                linkTo(methodOn(EmployeeAddressController.class).readOneEmployeeAddress(entity.getEmployeeId())).withSelfRel(),
                linkTo(methodOn(EmployeeAddressController.class).readAllEmployeeAddress()).withRel("address")
                );
    }

    @Override
    public @NotNull CollectionModel<EntityModel<EmployeeAddress>> toCollectionModel(@NotNull Iterable<? extends EmployeeAddress> entities) {
        return null;
    }
}
