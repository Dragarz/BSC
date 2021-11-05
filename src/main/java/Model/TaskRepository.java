package Model;

import java.util.ArrayList;
import java.util.List;

public class TaskRepository implements Repository {
    private final List<Task> tasks = new ArrayList<>();
    private int idGenerator = 0;

    public List<Task> getAllTasks() {
        return tasks;
    }

    public void addTask(Task task) {
        idGenerator++;
        task.setId(Integer.toString(idGenerator));
        tasks.add(task);
    }

    public void removeTask(Task task) {
        tasks.remove(task);
    }


}
