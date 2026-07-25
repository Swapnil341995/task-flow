package taskflow.mapper;

import org.springframework.stereotype.Component;
import taskflow.dto.UserRequestDto;
import taskflow.dto.UserResponseDto;
import taskflow.entity.User;

@Component
public class UserMapper {
    public static User toEntity(UserRequestDto dto){
        User user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        return user;
    }

    public static UserResponseDto toResponse(User user){
        UserResponseDto responseDto = new UserResponseDto();
        responseDto.setId(user.getId());
        responseDto.setName(user.getName());
        responseDto.setEmail(user.getEmail());
        return responseDto;
    }
}
