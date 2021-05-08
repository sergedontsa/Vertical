package real.prop.vertical.Repository.Complain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import real.prop.vertical.Tuples.Complain.Complaindone;

@Repository
public interface ComplainDoneRepository extends JpaRepository<Complaindone, Integer>{}
