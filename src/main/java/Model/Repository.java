package Model;

import java.util.List;

public interface Repository {
    List<Task> getAllTasks();

    void addTask(Task task);

    void removeTask(Task task);
}
