package taskflow.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import taskflow.dto.task.TaskRequestDto;
import taskflow.dto.task.TaskResponseDto;
import taskflow.entity.Project;
import taskflow.entity.Task;
import taskflow.exception.ResourceNotFoundException;
import taskflow.mapper.TaskMapper;
import taskflow.repository.ProjectRepository;
import taskflow.repository.TaskRepository;

import java.util.List;

@Service
public class TaskService {
    private final TaskRepository taskRepository;
    private final ProjectRepository projectRepository;

    private Project findProject(Long projectId) {
        return projectRepository.findById(projectId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Project not found with id " + projectId));
    }

    private Task findTask(Long taskId, Long projectId) {
        return taskRepository.findByIdAndProjectId(taskId, projectId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Task not found with id " + taskId));
    }

    @Autowired
    public TaskService(TaskRepository taskRepository, ProjectRepository projectRepository) {
        this.taskRepository = taskRepository;
        this.projectRepository = projectRepository;
    }

    public TaskResponseDto createTask(Long projectId,
                                      TaskRequestDto taskRequestDto) {
        Project existingProject = findProject(projectId);
        Task task = TaskMapper.toEntity(taskRequestDto);
        existingProject.addTask(task);
        Task savedTask = taskRepository.save(task);
        return TaskMapper.toResponse(savedTask);
    }

    public List<TaskResponseDto> getTaskList(Long projectId) {
        Project existingProject = findProject(projectId);
        return existingProject
                .getTasks()
                .stream()
                .map(TaskMapper::toResponse)
                .toList();
    }

    public TaskResponseDto getTask(Long projectId, Long taskId) {
        Task task = findTask(taskId, projectId);
        return TaskMapper.toResponse(task);
    }

    public TaskResponseDto updateTask(Long projectId, Long taskId, TaskRequestDto requestDto) {
        Task task = findTask(taskId, projectId);
        TaskMapper.updateTask(task, requestDto);
        Task savedTask = taskRepository.save(task);
        return TaskMapper.toResponse(savedTask);
    }

    public void deleteTask(Long projectId, Long taskId) {
        Task task = findTask(taskId, projectId);
        taskRepository.delete(task);
    }
}
