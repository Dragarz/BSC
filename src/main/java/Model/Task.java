package Model;

import lombok.Data;


@Data
public class Task {
    private String taskText;
    private boolean completed;
    private String id;

    public Task(String task, boolean completed) {
        this.taskText = task;
        this.completed = completed;
    }

    public String toString() {
        return String.format("%s. %s %s%n", id, completed ? "[X]" : "[ ]", taskText);
    }

}
