package Model;

import java.util.Map;

public interface Repository {
    Map<String, Task> getAllTasks();
    int getId();
    void addTask(String idTask, Task task);
    void removeTask(String id);
}
