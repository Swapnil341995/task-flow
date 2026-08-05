package taskflow.mapper;

import taskflow.dto.project.ProjectResponseDto;
import taskflow.dto.task.TaskRequestDto;
import taskflow.dto.task.TaskResponseDto;
import taskflow.entity.Task;

public class TaskMapper {
    public static Task toEntity(TaskRequestDto requestDto){
        Task task = new Task();
        task.setTitle(requestDto.getTitle());
        task.setDescription(requestDto.getDescription());
        task.setStatus(requestDto.getStatus());
        task.setPriority(requestDto.getPriority());
        task.setDueDate(requestDto.getDueDate());
        return task;
    }

    public static TaskResponseDto toResponse(Task task){
        TaskResponseDto taskResponseDto = new TaskResponseDto();
        taskResponseDto.setId(task.getId());
        taskResponseDto.setTitle(task.getTitle());
        taskResponseDto.setDescription(task.getDescription());
        taskResponseDto.setStatus(task.getStatus());
        taskResponseDto.setPriority(task.getPriority());
        taskResponseDto.setDueDate(task.getDueDate());
        return taskResponseDto;
    }

    public static Task updateTask(Task task, TaskRequestDto requestDto){
        task.setTitle(requestDto.getTitle());
        task.setDescription(requestDto.getDescription());
        task.setDueDate(requestDto.getDueDate());
        task.setPriority(requestDto.getPriority());
        task.setStatus(requestDto.getStatus());
        return task;
    }
}
