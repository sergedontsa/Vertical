package real.prop.vertical.Building;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import real.prop.vertical.Repository.Building.BuildingRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class BuildingControllerTest {

    @Autowired
    BuildingRepository buildingRepository;

    @Test
    public void testSave(){
//        Building building = new Building();
//        building.setBuildingId(IDGenerator.BUILDING_ID());
//        Building saved = buildingRepository.save(building);
//
//        assertNotNull(saved.getBuildingId());

    }

}
