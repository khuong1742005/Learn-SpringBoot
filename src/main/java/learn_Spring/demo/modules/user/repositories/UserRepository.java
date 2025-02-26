package learn_Spring.demo.modules.user.repositories;

import learn_Spring.demo.modules.user.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
