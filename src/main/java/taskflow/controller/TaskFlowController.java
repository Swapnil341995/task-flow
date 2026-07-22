package taskflow.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TaskFlowController {

    @GetMapping("/health")
    public String checkHealth(){
        return "TaskFlow API is running!";
    }
}
