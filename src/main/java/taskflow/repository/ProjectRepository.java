package taskflow.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import taskflow.entity.Project;

public interface ProjectRepository extends JpaRepository<Project, Long> {
}
