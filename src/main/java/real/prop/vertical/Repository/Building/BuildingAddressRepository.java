package real.prop.vertical.Repository.Building;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import real.prop.vertical.Tuples.Building.BuildingAddress;


@Repository
public interface BuildingAddressRepository extends JpaRepository<BuildingAddress, Integer>
{
    @Query("SELECT b FROM BuildingAddress b WHERE b.buildingid=?1")
    BuildingAddress queryBuildingAddressByBuildingId(String buildingId);
}
