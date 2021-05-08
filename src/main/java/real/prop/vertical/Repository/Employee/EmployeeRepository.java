package real.prop.vertical.Repository.Employee;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import real.prop.vertical.Tuples.Employee.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String> {
}
