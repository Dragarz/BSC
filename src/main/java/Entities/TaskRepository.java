package Entities;

import java.util.HashMap;
import java.util.Map;

public class TaskRepository{
    private Map<String, Task> tasks = new HashMap<>();

    public Map getTasks(){
        return tasks;
    }

}
