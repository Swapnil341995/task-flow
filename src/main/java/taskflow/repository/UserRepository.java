package taskflow.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import taskflow.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
