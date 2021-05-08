package real.prop.vertical.Repository.ContactInformation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import real.prop.vertical.Tuples.ContactInformation.ContactInformation;

@Repository
public interface ContactInformationRepository extends JpaRepository<ContactInformation, Integer> {
}
