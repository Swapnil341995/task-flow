package taskflow.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import taskflow.entity.Project;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {
}
