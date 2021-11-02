import java.util.HashMap;
import java.util.Map;

public class TaskService implements Service{
    private static final String PRINT = "print";
    private static Map<Integer, Task> Tasks = new HashMap<>();
    private static int id = 1;

    public TaskService(){

    }

    @Override
    public void add(String task) {
        Tasks.put(id++, new TaskCreater(task));
    }
    @Override
    public void toggle(String id) {

        Task task = Tasks.get(Integer.parseInt(id));
        if (task != null) {
            task.switchCompleted();
            Main.logger.debug("Входящий параметр id = " + id + ", результат: Task complited - " + task.getCompleted());
        } else {
            try{
                throw new RuntimeException();
            }catch (RuntimeException e) {
                Main.logger.error("Введен не существующий id - " + id);
                System.err.println("Задачи по данному id не существует.");
            }
        }
    }
    @Override
    public void print(String task) {

        if (task.length() == PRINT.length()) {
            Tasks.entrySet().stream()
                    .filter(a -> !a.getValue().getCompleted())
                    .forEach(a -> System.out.printf("%d. [ ] %s%n", a.getKey(), a.getValue().getTaskName()));

        } else {
            Tasks.forEach((key, value) -> System.out.printf("%d. %s %s%n", key, value.getCompleted() ? "[X]" : "[ ]", value.getTaskName()));
        }

    }
    @Override
    public void delete(String id) {
        Task task = Tasks.get(Integer.parseInt(id));
        if (task != null) {
            Tasks.remove(Integer.parseInt(id));
        } else {
            System.err.println("Задачи по данному id не существует.");
        }


    }
    @Override
    public void edit(String command) {
        int id = Integer.parseInt(command.split(" ")[1]);
        Task task = Tasks.get(id);
        if (task != null) {
            task.setTaskName(command.substring(command.indexOf(Integer.toString(id)) + 1).trim());
            task.setCompleted(false);
        } else {
            System.err.println("Задачи по данному id не существует.");
        }
    }
    @Override
    public void search(String substring) {
        Tasks.entrySet().stream()
                .filter(a -> a.getValue().getTaskName().contains(substring))
                .forEach(a -> System.out.printf("%d. [ ] %s%n", a.getKey(), a.getValue().getTaskName()));

    }

}
