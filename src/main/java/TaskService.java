import java.util.HashMap;
import java.util.Map;

public class TaskService {
    private static final String PRINT = "print";
    private static Map<Integer, TaskService> Tasks = new HashMap<>();
    private static int id = 1;
    private String task;
    private Boolean completed;

    private TaskService(String task) {
        Tasks.put(id++, this);
        this.task = task;
        this.completed = false;

    }

    public static void add(String task) {
        new TaskService(task);
    }

    public static void toggle(String id) {

        TaskService task = Tasks.get(Integer.parseInt(id));
        if (task != null) {
            task.completed = !task.completed;
        } else {
            System.err.println("Задачи по данному id не существует.");
        }
    }

    public static void print(String task) {

        if (task.length() == PRINT.length()) {
            Tasks.entrySet().stream()
                    .filter(a -> !a.getValue().completed)
                    .forEach(a -> System.out.printf("%d. [ ] %s%n", a.getKey(), a.getValue().task));

        } else {
            Tasks.forEach((key, value) -> System.out.printf("%d. %s %s%n", key, value.completed ? "[X]" : "[ ]", value.task));
        }

    }

    public static void delete(String id) {
        TaskService task = Tasks.get(Integer.parseInt(id));
        if (task != null) {
            Tasks.remove(Integer.parseInt(id));
        } else {
            System.err.println("Задачи по данному id не существует.");
        }


    }

    public static void edit(String command) {
        int id = Integer.parseInt(command.split(" ")[1]);
        TaskService task = Tasks.get(id);
        if (task != null) {
            task.task = command.substring(command.indexOf(Integer.toString(id)) + 1).trim();
            task.completed = false;
        } else {
            System.err.println("Задачи по данному id не существует.");
        }
    }

    public static void search(String substring) {
        Tasks.entrySet().stream()
                .filter(a -> a.getValue().task.contains(substring))
                .forEach(a -> System.out.printf("%d. [ ] %s%n", a.getKey(), a.getValue().task));

    }

}
