package Service;

import Model.Repository;
import Model.Task;
import Utils.ParserUtil;
import java.util.Map;

public class TaskService implements Service {
    private final Repository tasksRepository;

    public TaskService(Repository tasks){
        this.tasksRepository = tasks;
    }

    @Override
    public void add(String taskText) {
        Task task = new Task(taskText, false);
        tasksRepository.addTask(task.getId(), task);
    }

    @Override
    public void toggle(String id) {
        if(tasksRepository.getAllTasks().get(id) != null){
            Task task = tasksRepository.getAllTasks().get(id);
            task.setCompleted(!task.getCompleted());
        }else{
            throw new NullPointerException("Эллемена с данным id "+ id + " Не существует");
        }
    }

    @Override
    public void print(String command) {
        if(!command.equals("all")){
            tasksRepository.getAllTasks().entrySet().stream()
                    .filter(a -> !a.getValue().getCompleted())
                    .forEach(a -> System.out.print(a.getValue()));
        }else{
            tasksRepository.getAllTasks().entrySet().forEach(a -> System.out.print(a.getValue()));
        }
    }

    @Override
    public void delete(String id) {
        if(tasksRepository.getAllTasks().get(id) != null){
            tasksRepository.getAllTasks().remove(id);
        }else{
            throw new NullPointerException("Эллемена с данным id "+ id + " Не существует");
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
            throw new NullPointerException("Эллемена с данным id "+ command.split(" ")[0] + " Не существует");
        }
    }

    @Override
    public void search(String substring) {
        tasksRepository.getAllTasks().entrySet().stream()
                .filter(a -> a.getValue().getTask().contains(substring))
                .forEach(a -> System.out.print(a.getValue()));
    }

    @Override
    public Map<String, Task> getTasks(){
        return null;
    }
}
