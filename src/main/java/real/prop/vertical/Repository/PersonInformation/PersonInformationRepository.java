package real.prop.vertical.Repository.PersonInformation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import real.prop.vertical.Tuples.Personinformation;

@Repository
public interface PersonInformationRepository extends JpaRepository<Personinformation, String>
{

}
