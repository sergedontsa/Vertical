package real.prop.vertical;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;
import real.prop.vertical.Tuples.Apartment.ApartmentDocument;
import real.prop.vertical.Tuples.Building.BuildingDocument;
import real.prop.vertical.Tuples.Employee.EmployeeDocument;
import real.prop.vertical.Tuples.Tenant.TenantDocument;

@SpringBootApplication
@EnableScheduling
@EnableJpaAuditing
@EnableConfigurationProperties({ EmployeeDocument.class, ApartmentDocument.class, TenantDocument.class,
        BuildingDocument.class })
@ConfigurationPropertiesScan("real.prop.vertical")
public class VerticalApplication {

    public static void main(String[] args) {
        SpringApplication.run(VerticalApplication.class, args);
        System.out.println("Vertical Starting");
    }

}
