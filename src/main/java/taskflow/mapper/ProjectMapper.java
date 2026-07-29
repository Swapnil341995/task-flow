package taskflow.mapper;
import taskflow.dto.project.ProjectRequestDto;
import taskflow.dto.project.ProjectResponseDto;
import taskflow.entity.Project;

public class ProjectMapper {
    public static ProjectResponseDto toResponse(Project project){
        ProjectResponseDto responseDto = new ProjectResponseDto();
        responseDto.setId(project.getId());
        responseDto.setName(project.getName());
        responseDto.setDescription(project.getDescription());
        return responseDto;
    }

    public static Project toEntity(ProjectRequestDto requestDto){
        Project project = new Project();
        project.setName(requestDto.getName());
        project.setDescription(requestDto.getDescription());
        return project;
    }

    public static Project updateProject(Project existingProject, ProjectRequestDto dto){
        existingProject.setName(dto.getName());
        existingProject.setDescription(dto.getDescription());
        return existingProject;
    }
}
