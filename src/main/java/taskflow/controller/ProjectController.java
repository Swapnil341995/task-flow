package taskflow.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import taskflow.dto.project.ProjectRequestDto;
import taskflow.dto.project.ProjectResponseDto;
import taskflow.service.ProjectService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProjectController {
    private final ProjectService projectService;

    @Autowired
    public ProjectController(ProjectService projectService){
        this.projectService = projectService;
    }

    @PostMapping("/users/{userId}/projects")
    public ResponseEntity<ProjectResponseDto> createProject(@PathVariable Long userId, @Valid @RequestBody ProjectRequestDto requestDto){
        ProjectResponseDto responseDto = this.projectService.createProject(userId, requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @GetMapping("/users/{userId}/projects")
    public List<ProjectResponseDto> getAllProjects(@PathVariable Long userId){
        return this.projectService.getAllProjects(userId);
    }

    @GetMapping("/projects/{projectId}")
    public ProjectResponseDto getProject(@PathVariable Long projectId){
        return this.projectService.getProject(projectId);
    }

    @PutMapping("/projects/{projectId}")
    public ResponseEntity<ProjectResponseDto> updateProject(@PathVariable Long projectId,@Valid @RequestBody ProjectRequestDto requestDto){
        ProjectResponseDto responseDto = this.projectService.updateProject(projectId, requestDto);
        return ResponseEntity.ok(responseDto);
    }

    @DeleteMapping("/projects/{projectId}")
    public ResponseEntity<String> deleteProject(@PathVariable Long projectId){
        this.projectService.deleteProject(projectId);
        return ResponseEntity.status(HttpStatus.OK).body("Project deleted successfully");
    }
}
