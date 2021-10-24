import java.util.HashMap;
import java.util.Map;

public class TaskService {
    private static Map<Integer, TaskService> TaskMap = null;
    private static int id = 1;
    private String task;
    private Boolean completed;

    private TaskService(String task) {
        if (TaskMap == null) {
            TaskMap = new HashMap<>();
        }
        TaskMap.put(id++, this);
        this.task = task;
        this.completed = false;

    }

    public static void add(String task) {
        new TaskService(task);
    }

    public static void toggle(String task) {

        TaskService obj = TaskMap.get(Integer.parseInt(task));
        if (obj != null) {
            obj.completed = !obj.completed;
        } else {
            System.err.println("Задачи по данному id не существует.");
        }
    }

    public static void print(String task) {
        if (task.length() == 5) {
            for (var s : TaskMap.keySet()) {
                if (!TaskMap.get(s).completed) {
                    System.out.printf("%d. [ ] %s%n", s, TaskMap.get(s).task);
                }
            }
        } else {
            for (var s : TaskMap.keySet()) {
                if (TaskMap.get(s) != null) {
                    System.out.printf("%d. %s %s%n", s, TaskMap.get(s).completed ? "[X]" : "[ ]", TaskMap.get(s).task);
                }
            }
        }

    }

    public static void delete(String id_s) {
        TaskService obj = TaskMap.get(Integer.parseInt(id_s));
        if (obj != null) {
            TaskMap.remove(Integer.parseInt(id_s));
        } else {
            System.err.println("Задачи по данному id не существует.");
        }


    }

    public static void edit(String command) {
        int ids = Integer.parseInt(command.split(" ")[1]);
        TaskService obj = TaskMap.get(ids);
        if (obj != null) {
            obj.task = command.substring(command.indexOf(Integer.toString(ids)) + 1).trim();
            obj.completed = false;
        } else {
            System.err.println("Задачи по данному id не существует.");
        }
    }

    public static void search(String substring) {
        for (var s : TaskMap.keySet()) {
            if (TaskMap.get(s).task.contains(substring) && !TaskMap.get(s).completed) {
                System.out.printf("%d. [ ] %s%n", s, TaskMap.get(s).task);
            }
        }
    }

}
