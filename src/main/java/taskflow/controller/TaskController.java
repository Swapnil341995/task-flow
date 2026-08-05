package taskflow.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import taskflow.dto.task.TaskRequestDto;
import taskflow.dto.task.TaskResponseDto;
import taskflow.entity.Task;
import taskflow.service.TaskService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TaskController {
    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService){
        this.taskService = taskService;
    }

    @PostMapping("/projects/{projectId}/tasks")
    public ResponseEntity<TaskResponseDto> createTask(@PathVariable Long projectId,
                                                      @Valid @RequestBody TaskRequestDto taskRequestDto){
       TaskResponseDto taskResponseDto =
               this.taskService.createTask(projectId, taskRequestDto);
       return new ResponseEntity<>(taskResponseDto, HttpStatus.CREATED);
    }

    @GetMapping("/projects/{projectId}/tasks")
    public List<TaskResponseDto> getTaskList(@PathVariable Long projectId){
        return this.taskService.getTaskList(projectId);
    }

    @GetMapping("/projects/{projectId}/tasks/{taskId}")
    public TaskResponseDto getTask(@PathVariable Long projectId,
                                   @PathVariable Long taskId){
        return this.taskService.getTask(projectId, taskId);
    }

    @PutMapping("/projects/{projectId}/tasks/{taskId}")
    public TaskResponseDto updateTask(@PathVariable Long projectId,
                                      @PathVariable Long taskId,
                                      @Valid @RequestBody TaskRequestDto requestDto){
        return this.taskService.updateTask(projectId, taskId, requestDto);
    }

    @DeleteMapping("/projects/{projectId}/tasks/{taskId}")
    public ResponseEntity<String> deleteTask(@PathVariable Long projectId,
                                     @PathVariable Long taskId){
        this.taskService.deleteTask(projectId, taskId);
        return ResponseEntity.noContent().build();
    }
}
