package taskflow;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TaskflowApplication {
	public static void main(String[] args) {
		System.out.println("STARTING TASKFLOW APPLICATION");
		SpringApplication.run(TaskflowApplication.class, args);
	}
}
