package Service;
import Model.Task;
import java.util.List;

public interface TaskService {
    void add(String task);

    void toggle(String id);

    void delete(String id);

    void edit(String id, String taskText);

    List<Task> getAllTasks();

    List<Task> getUnfinishedTask();

    List<Task> searchTasks(String substring);
}