package taskflow.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Project {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String description;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "project")
    private List<Task> tasks = new ArrayList<>();

    public Project(){

    }

    public void setUser(User user) {
        this.user = user;
    }

    public void addTask(Task task){
        this.tasks.add(task);
        task.setProject(this);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    public List<Task> getTasks(){
        return this.tasks;
    }

    public void setTasks(List<Task> tasks){
        this.tasks = tasks;
    }
}
