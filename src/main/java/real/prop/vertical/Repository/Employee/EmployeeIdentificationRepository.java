package real.prop.vertical.Repository.Employee;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import real.prop.vertical.Tuples.Employee.EmployeeIdentification;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeIdentificationRepository extends JpaRepository<EmployeeIdentification, Integer> {

    //https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#repositories.special-parameters
    @Query("SELECT e FROM EmployeeIdentification e WHERE e.employeeId=?1 ")
    Optional<EmployeeIdentification> getEmployeeIdentificationByEmployeeId(String employeeId);


    @Query("DELETE FROM EmployeeIdentification a WHERE a.employeeId=?1")
    void deleteByEmployeeId(String employeeId);

    //@Query(value = "SELECT a FROM EmployeeIdentification a where a.employeeId=?1", nativeQuery = true)
    @Query(value = "SELECT * FROM Employee_identification WHERE employeeId=?1", nativeQuery = true)
    List<EmployeeIdentification> getAllByEmployeeId(String employeeId);




}
