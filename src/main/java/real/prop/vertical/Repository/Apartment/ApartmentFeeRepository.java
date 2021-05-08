package real.prop.vertical.Repository.Apartment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import real.prop.vertical.Tuples.Apartment.ApartmentFee;

@Repository
public interface ApartmentFeeRepository extends JpaRepository<ApartmentFee, String> {
}
