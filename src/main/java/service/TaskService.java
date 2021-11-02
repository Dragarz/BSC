package service;

import Utlis.Constants;
import Utlis.Parser;
import model.Task;
import model.TaskID;
import model.TaskRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.stream.Collectors;

public class TaskService implements Service {
    public static final Logger logger = LoggerFactory.getLogger(TaskService.class.getName());
    private TaskRepository taskRepository = null;
    private Map<String, Task> tasks;
    private Task task = null;


    @Override
    public void add(String taskText) {
        if(taskRepository == null){
            taskRepository = new TaskRepository();
            tasks = taskRepository.getTasks();
        }
        tasks.put(TaskID.getID(),new Task(taskText));
    }

    @Override
    public void toggle(String id) {
        logger.debug("Введённый id: "+ id, this.getClass().getName());
        task = tasks.get(id);
        if (task != null) {
            task.setCompleted(!task.getCompleted());
        } else {
            logger.error("Задачи по данному id не существует.", id);
            throw new NullPointerException("Задачи по данному id не существует.");
        }

    }

    @Override
    public Map<String, Task> print(String command) {
        if (command.length() == Constants.PRINT.length()) {
            return tasks.entrySet().stream()
                    .filter(a -> !a.getValue().getCompleted()).collect(Collectors.toMap(a -> a.getKey(), a -> a.getValue()));
        } else {
            return tasks;
        }
    }

    @Override
    public void delete(String id) {
        if (tasks.get(id) != null) {
            tasks.remove(id);
        } else {
            logger.error("Задачи по данному id не существует.");
            throw new NullPointerException("Задачи по данному id не существует.");
        }
    }

    @Override
    public void edit(String command) {
        String id = command.split(" ")[0];
        if (tasks.get(id) != null) {
            tasks.put(id, new Task(Parser.ParseCommands(command)));
        } else {
            logger.error("Задачи по данному id не существует.");
            throw new NullPointerException("Задачи по данному id не существует.");
        }
    }

    @Override
    public Map<String, Task> search(String substring) {
        tasks = taskRepository.getTasks();
        return tasks.entrySet().stream()
                .filter(a -> a.getValue().getTask().contains(substring)).collect(Collectors.toMap(a -> a.getKey(), a -> a.getValue()));
    }
}
