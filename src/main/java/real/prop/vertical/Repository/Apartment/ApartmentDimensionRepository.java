package real.prop.vertical.Repository.Apartment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import real.prop.vertical.Tuples.Apartment.ApartmentDimension;

@Repository
public interface ApartmentDimensionRepository extends JpaRepository<ApartmentDimension, String> {
}
