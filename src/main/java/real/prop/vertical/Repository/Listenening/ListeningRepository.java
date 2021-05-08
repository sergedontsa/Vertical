package real.prop.vertical.Repository.Listenening;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import real.prop.vertical.Tuples.Listening.Listening;

@Repository
public interface ListeningRepository extends JpaRepository<Listening, String> {
}
