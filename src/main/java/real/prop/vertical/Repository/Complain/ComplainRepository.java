package real.prop.vertical.Repository.Complain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import real.prop.vertical.Tuples.Complain.Complain;

@Repository
public interface ComplainRepository extends JpaRepository<Complain, String> {
}
