package taskflow.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import taskflow.dto.UserResponseDto;
import taskflow.dto.project.ProjectRequestDto;
import taskflow.dto.project.ProjectResponseDto;
import taskflow.entity.Project;
import taskflow.entity.User;
import taskflow.exception.ResourceNotFoundException;
import taskflow.mapper.ProjectMapper;
import taskflow.repository.ProjectRepository;
import taskflow.repository.UserRepository;

import java.util.List;

@Service
public class ProjectService {
    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;

    @Autowired
    public ProjectService(ProjectRepository projectRepository, UserRepository userRepository){
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
    }

    private User findUser(Long userId){
        return userRepository.findById(userId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Project not found with id "+userId));
    }

    private Project findProject(Long projectId){
        return projectRepository.findById(projectId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Project not found with id "+projectId));
    }

    public ProjectResponseDto createProject(Long userId, ProjectRequestDto projectRequestDto){
        User existingUser = findUser(userId);
        Project project = ProjectMapper.toEntity(projectRequestDto);
        existingUser.addProject(project);
        Project savedProject = projectRepository.save(project);
        return ProjectMapper.toResponse(savedProject);
    }

    public List<ProjectResponseDto> getAllProjects(Long userId){
        User existingUser = findUser(userId);
        List<Project> userProjects = existingUser.getProjects();
        return userProjects
                .stream()
                .map(ProjectMapper::toResponse)
                .toList();
    }

    public ProjectResponseDto getProject(Long projectId){
        Project existingProject = this.findProject(projectId);
        return ProjectMapper.toResponse(existingProject);
    }

    public ProjectResponseDto updateProject(Long projectId, ProjectRequestDto requestDto){
        Project existingProject = this.findProject(projectId);
        ProjectMapper.updateProject(existingProject, requestDto);
        Project savedProject = projectRepository.save(existingProject);
        return ProjectMapper.toResponse(savedProject);
    }

    public void deleteProject(Long projectId){
        Project existingProject = this.findProject(projectId);
        projectRepository.delete(existingProject);
    }
}
