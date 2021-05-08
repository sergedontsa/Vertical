package real.prop.vertical.Repository.Subscriber;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import real.prop.vertical.Tuples.User.Subscriber;

@Repository
public interface SubscriberRepository extends JpaRepository<Subscriber, Integer> {
}
