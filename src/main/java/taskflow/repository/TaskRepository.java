package taskflow.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import taskflow.entity.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
