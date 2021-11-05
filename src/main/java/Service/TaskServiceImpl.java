package Service;

import Model.Repository;
import Model.Task;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class TaskServiceImpl implements TaskService {
    private final Repository tasksRepository;

    public TaskServiceImpl(Repository tasks) {
        this.tasksRepository = tasks;
    }

    @Override
    public void add(String taskText) {
        log.info("вызван метод add({})", taskText);
        Task task = new Task(taskText, false);
        tasksRepository.addTask(task);
    }

    @Override
    public void toggle(String id) {
        log.info("вызван метод toggle({})", id);
        Task task = tasksRepository.getAllTasks().stream()
                .filter(a -> a.getId().equals(id))
                .collect(Collectors.toList()).get(0);
        task.setCompleted(!task.isCompleted());

    }


    @Override
    public void delete(String id) {
        log.info("вызван метод delete({})", id);
        Task task = tasksRepository.getAllTasks().stream()
                .filter(a -> a.getId().equals(id)).collect(Collectors.toList()).get(0);
        tasksRepository.removeTask(task);
    }

    @Override
    public void edit(String id, String taskText) {
        log.info("вызван метод edit({}, {})", id, taskText);
        Task task = tasksRepository.getAllTasks().stream()
                .filter(a -> a.getId().equals(id)).collect(Collectors.toList()).get(0);
        task.setTaskText(taskText);
    }

    @Override
    public List<Task> searchTasks(String substring) {
        log.info("вызван метод searchTasks({})", substring);
        return tasksRepository.getAllTasks().stream()
                .filter(a -> a.getTaskText().contains(substring))
                .collect(Collectors.toList());
    }

    @Override
    public List<Task> getAllTasks() {
        return tasksRepository.getAllTasks();
    }

    @Override
    public List<Task> getUnfinishedTask() {
        return tasksRepository.getAllTasks().stream().filter(a -> !a.isCompleted()).collect(Collectors.toList());
    }

}
