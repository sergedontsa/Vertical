package real.prop.vertical.Repository.Employee;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import real.prop.vertical.Tuples.Employee.EmployeeAddress;

import java.util.Optional;

@Repository
public interface EmployeeAddressRepository extends JpaRepository<EmployeeAddress, Integer> {

    @Query("SELECT a FROM EmployeeAddress a WHERE a.employeeId = ?1")
    Optional<EmployeeAddress> getAddressWithEmployeeId(String employeeId);

    @Transactional
    @Modifying
    @Query("DELETE FROM EmployeeAddress e WHERE e.employeeId=?1")
    void deleteByEmployeeId( String employeeId);


}
