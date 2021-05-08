package real.prop.vertical.Controllers.Employee;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import real.prop.vertical.Enum.EmployeeStatus;
import real.prop.vertical.Repository.Employee.EmployeeRepository;
import real.prop.vertical.Resources.Constant;
import real.prop.vertical.Resources.IDGenerator;
import real.prop.vertical.TupleAssembler.Employee.EmployeeModelAssembler;
import real.prop.vertical.Tuples.Employee.Employee;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(value = Constant.EMPLOYEE_CONTROLLER)
public class EmployeeController {
    private static final Logger  logger = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    private final EmployeeRepository employeeRepository;

    @Autowired private final EmployeeModelAssembler employeeModelAssembler;

    public EmployeeController(EmployeeRepository employeeRepository, EmployeeModelAssembler employeeModelAssembler) {
        this.employeeRepository = employeeRepository;
        this.employeeModelAssembler = employeeModelAssembler;
    }

    @CrossOrigin
    @GetMapping(value = Constant.EMPLOYEE_GET_ALL, produces = Constant.PRODUCE)
    public CollectionModel<EntityModel<Employee>> readAllEmployee(){
        logger.info("[READ ALL]:[EMPLOYEE] ");
        List<EntityModel<Employee>> employees = this.employeeRepository
                                                        .findAll()
                                                        .stream()
                                                        .map(this.employeeModelAssembler::toModel).collect(Collectors.toList());

        return new CollectionModel<>(employees, linkTo(methodOn(EmployeeController.class)
                                                               .readAllEmployee())
                                                        .withSelfRel());
    }

    @CrossOrigin
    @GetMapping(value = Constant.EMPLOYEE_GET_WITH_ID, produces = Constant.PRODUCE)
    public EntityModel<Employee> readOneEmployee(@PathVariable String id){
        logger.info("[READ ONE]:[EMPLOYEE] " +  id);

        if (this.employeeRepository.findById(id).isPresent()){
            return this.employeeModelAssembler.toModel(
                    this.employeeRepository.findById(id).get()
            );
        }else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }


    }

    @CrossOrigin
    @PostMapping(value = Constant.EMPLOYEE_SAVE, consumes = Constant.CONSUMES)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> createOneEmployee(@RequestBody Employee employee){
        logger.info("[CREATE]:[EMPLOYEE]: " + employee);
        String id;
        do {
            id = IDGenerator.EMPLOYEE_ID();
        }while (this.employeeRepository.findById(id).isPresent());

        employee.setEmployeeId(IDGenerator.EMPLOYEE_ID());
        employee.setStatus(String.valueOf(EmployeeStatus.PENDING));
        EntityModel<Employee> entity = this.employeeModelAssembler
                .toModel(this.employeeRepository.save(employee));

        return ResponseEntity.created(this.employeeModelAssembler.toModel(this.employeeRepository
        .save(employee)).getRequiredLink(IanaLinkRelations.SELF)
        .toUri())
                .body(entity);

    }

    @CrossOrigin
    @DeleteMapping(value = Constant.EMPLOYEE_DELETE_WITH_ID)
    public ResponseEntity<?> deleteEmployee(@PathVariable String id){
        logger.warn("[DELETE]:[EMPLOYEE]:ID" + id);

        if (!this.employeeRepository.findById(id).isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            this.employeeRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }


    }

    @CrossOrigin
    @ResponseStatus(code = HttpStatus.OK)
    @PatchMapping(path = Constant.EMPLOYEE_UPDATE_WITH_ID, consumes = Constant.CONSUMES)
    public Object updateEmployee(@PathVariable String id, @RequestBody Employee employee){
        logger.info("[UPDATE]:[EMPLOYEE]:ID" + id );

        if (!this.employeeRepository.findById(id).isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            employee.setEmployeeId(id);
            this.employeeRepository.save(employee);
            return this.employeeModelAssembler.toModel(this.employeeRepository.findById(id).get());
        }

    }







}
