package taskflow.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import taskflow.entity.Task;

import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Long> {
    Optional<Task> findByIdAndProjectId(Long id, Long projectId);
}
