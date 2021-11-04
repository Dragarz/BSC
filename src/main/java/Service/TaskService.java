package Service;

import Model.Repository;
import Model.Task;
import Utils.ParserUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.stream.Collectors;

public class TaskService implements Service {
    public static final Logger logger = LoggerFactory.getLogger(TaskService.class.getName());
    private final Repository tasksRepository;

    public TaskService(Repository tasks) {
        this.tasksRepository = tasks;
    }

    @Override
    public void add(String taskText) {
        Task task = new Task(taskText, false);
        tasksRepository.addTask(task.getId(), task);
    }

    @Override
    public void toggle(String id) {
        if (tasksRepository.getAllTasks().get(id) != null) {
            Task task = tasksRepository.getAllTasks().get(id);
            task.setCompleted(!task.getTaskCondition());
        } else {
            logger.debug("в методе (toggle) произошла ошибка, Эллемента с данным id " + id + " Не существует!",this);
        }
    }


    @Override
    public void delete(String id) {
        if (tasksRepository.getAllTasks().get(id) != null) {
            tasksRepository.getAllTasks().remove(id);
        } else {
            throw new NullPointerException("Эллемена с данным id " + id + " Не существует");
        }
    }

    @Override
    public void edit(String command) {
        String id = command.split(" ")[0].trim().trim();
        if (tasksRepository.getAllTasks().containsKey(id)) {
            Task task = tasksRepository.getAllTasks().get(id);
            task.setTask(ParserUtil.parseCommands(command));
            task.setCompleted(false);
        } else {
            throw new NullPointerException("Эллемена с данным id " + command.split(" ")[0] + " Не существует");
        }
    }


    @Override
    public Map<String, Task> getTasks(String command) {

        if (command.equals("all")) {
            return tasksRepository.getAllTasks();

        } else if(command.length() > 0){
           return tasksRepository.getAllTasks().entrySet().stream()
                    .filter(a -> a.getValue().getTask().contains(command))
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        }
        return tasksRepository.getAllTasks().entrySet().stream()
                .filter(a -> !a.getValue().getTaskCondition())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

}
