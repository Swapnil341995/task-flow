package taskflow.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import taskflow.dto.UserRequestDto;
import taskflow.dto.UserResponseDto;
import taskflow.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/users")
    public ResponseEntity<UserResponseDto> createUser(@Valid @RequestBody UserRequestDto requestDto){
        UserResponseDto responseDto = this.userService.createUser(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @PutMapping("/users/{userId}")
    public UserResponseDto updateUser(@PathVariable Long userId,@Valid @RequestBody UserRequestDto requestDto){
        return this.userService.updateUser(userId, requestDto);
    }

    @GetMapping("/users")
    public List<UserResponseDto> getAllUsers(){
        return this.userService.getAllUsers();
    }

    @GetMapping("/users/{userId}")
    public UserResponseDto getUser(@PathVariable Long userId){
        return this.userService.getUser(userId);
    }

    @DeleteMapping("/users/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable Long userId){
        this.userService.deleteUser(userId);
        return ResponseEntity.status(HttpStatus.OK).body("user deleted successfully");
    }
}