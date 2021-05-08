package real.prop.vertical.Repository.Building;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import real.prop.vertical.Tuples.Building.Building;

@Repository
public interface BuildingRepository extends JpaRepository<Building, String> {
}
