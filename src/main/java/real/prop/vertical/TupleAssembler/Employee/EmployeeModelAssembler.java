package real.prop.vertical.TupleAssembler.Employee;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import real.prop.vertical.Controllers.Employee.EmployeeController;
import real.prop.vertical.Tuples.Employee.Employee;

import javax.validation.constraints.NotNull;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class EmployeeModelAssembler implements RepresentationModelAssembler<Employee, EntityModel<Employee>>{


    @Override
    public @NotNull EntityModel<Employee> toModel(@NotNull Employee entity) {
        return new EntityModel<>(entity,
                linkTo(methodOn(EmployeeController.class).readOneEmployee(entity.getEmployeeId())).withSelfRel(),
                linkTo(methodOn(EmployeeController.class).readAllEmployee()).withRel("employees")
                );
    }

    @Override
    public @NotNull CollectionModel<EntityModel<Employee>> toCollectionModel(@NotNull Iterable<? extends Employee> entities) {
        return null;
    }
}

