package taskflow.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import taskflow.dto.UserRequestDto;
import taskflow.dto.UserResponseDto;
import taskflow.entity.User;
import taskflow.exception.ResourceNotFoundException;
import taskflow.mapper.UserMapper;
import taskflow.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    private User findUser(Long userId){
        return userRepository.findById(userId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found with id "+userId));
    }

    public UserResponseDto createUser(UserRequestDto requestDto){
        User user = UserMapper.toEntity(requestDto);
        User savedUser = userRepository.save(user);
        return UserMapper.toResponse(savedUser);
    }

    public UserResponseDto updateUser(Long userId, UserRequestDto requestDto){
        User existingUser = this.findUser(userId);
        User updatedUser = UserMapper.updateUser(existingUser, requestDto);
        User savedUser = userRepository.save(updatedUser);
        return UserMapper.toResponse(savedUser);
    }

    public List<UserResponseDto> getAllUsers(){
        List<User> users = userRepository.findAll();
        return users.stream().map(UserMapper::toResponse).toList();
    }

    public UserResponseDto getUser(Long userId){
        User user = this.findUser(userId);
        return UserMapper.toResponse(user);
    }

    public void deleteUser(Long userId){
        User existingUser = this.findUser(userId);
        userRepository.delete(existingUser);
    }
}
