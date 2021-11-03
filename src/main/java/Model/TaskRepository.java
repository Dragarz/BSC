package Model;

import java.util.HashMap;
import java.util.Map;

public class TaskRepository implements Repository{
    private Map<String, Task> tasks = new HashMap<>();
    private Integer id = 0;

    public Map getAllTasks(){
        return tasks;
    }
    public int getId(){ return id;}

    public void addTask(String idTask, Task task){
        id += Integer.parseInt(idTask);
        task.setId(id.toString());
        tasks.put(task.getId(), task);
    }
    public void removeTask(String id){
        tasks.remove(id);
    }


}
