package taskflow.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import taskflow.dto.UserRequestDto;
import taskflow.dto.UserResponseDto;
import taskflow.entity.User;
import taskflow.mapper.UserMapper;
import taskflow.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Autowired
    public UserService(UserRepository userRepository, UserMapper userMapper){
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public UserResponseDto createUser(UserRequestDto requestDto){
        User user = UserMapper.toEntity(requestDto);
        User savedUser = userRepository.save(user);
        return UserMapper.toResponse(savedUser);
    }

    public UserResponseDto updateUser(Long userId, UserRequestDto requestDto){
        Optional<User> optionalUser = userRepository.findById(userId);
        if(optionalUser.isEmpty()){
            throw new RuntimeException("user not found with id: "+userId);
        }
        User savedUser = UserMapper.toEntity(requestDto);
        User updatedUser = userRepository.save(savedUser);
        return UserMapper.toResponse(updatedUser);
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User getUser(Long userId){
        Optional<User> savedUser = userRepository.findById(userId);
        return savedUser.orElseThrow(() -> new RuntimeException("user not found with id: "+userId));
    }

    public void deleteUser(Long userId){
        Optional<User> optionalUser = userRepository.findById(userId);
        User savedUser = optionalUser.orElseThrow(() -> new RuntimeException("user not found with id: "+userId));
        userRepository.delete(savedUser);
    }
}
