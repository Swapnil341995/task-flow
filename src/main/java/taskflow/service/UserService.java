package taskflow.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import taskflow.entity.User;
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

    public User createUser(User user){
        return userRepository.save(user);
    }

    public User updateUser(Long userId, User user){
        Optional<User> optionalUser = userRepository.findById(userId);
        User savedUser = optionalUser.orElseThrow(() -> new RuntimeException("user not found with id: "+userId));
        savedUser.setName(user.getName());
        savedUser.setEmail(user.getEmail());
        savedUser.setPassword(user.getPassword());
        return userRepository.save(savedUser);
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
