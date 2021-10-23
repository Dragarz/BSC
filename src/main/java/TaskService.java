import java.util.HashMap;
import java.util.Map;

public class TaskService {
    private static Map<Integer, TaskService> TaskMap = null;
    private static int id = 1;
    private String task = null;
    private Boolean completed = null;

    private TaskService(String task) {
        if (TaskMap == null) {
            TaskMap = new HashMap<>();
        }
        TaskMap.put(id++, this);
        this.task = task;
        this.completed = false;

    }

    public static void add(String task) {
        if (task != null) {
            new TaskService(task);
        } else {
            System.err.print("Попытка создать пустую задачу повторите ввод: ");
        }
    }

    public static void toggle(String[] parse) {
        try {
            if (parse.length <= 2) {
                if (TaskMap.get(Integer.parseInt(parse[1])) != null) {
                    TaskMap.get(Integer.parseInt(parse[1])).completed = !TaskMap.get(Integer.parseInt(parse[1])).completed;
                    return;
                } else {
                    System.err.println("Задачи по данному id не существует.");
                    return;
                }
            }
            System.err.print("Не правильный формат ввода. Повторите команду: ");

        } catch (RuntimeException e) {
            System.err.print("В качестве id введено не число. Повторите команду: ");
        }
    }

    public static void print(String[] parse) {
        if (parse.length == 1) {
            for (var s : TaskMap.keySet()) {
                if (!TaskMap.get(s).completed) {
                    System.out.println(String.format("%d. [ ] %s", s, TaskMap.get(s).task));
                }
            }
            return;
        } else if (parse.length == 2 && parse[1].equals("all")) {
            for (var s : TaskMap.keySet()) {
                if (TaskMap.get(s) != null) {
                    System.out.println(String.format("%d. %s %s", s, TaskMap.get(s).completed ? "[X]" : "[ ]", TaskMap.get(s).task));
                }
            }
            return;
        }
        System.err.print("Введена не корректная команда печати задач повторите ввод: ");
    }

    public static void delete(String[] parse) {
        try {
            if (parse.length <= 2) {
                if (TaskMap.get(Integer.parseInt(parse[1])) != null) {
                    TaskMap.remove(Integer.parseInt(parse[1]));
                    return;
                } else {
                    System.err.println("Задачи по данному id не существует.");
                    return;
                }
            }
            System.err.print("Не правильный формат ввода. Повторите команду: ");

        } catch (RuntimeException e) {
            System.err.print("В качестве id введено не число. Повторите команду: ");
        }
    }

    public static void edit(String[] parse, String task) {
        if (task != null) {
            try {
                int ids = Integer.parseInt(parse[1]);
                if (TaskMap.get(ids) != null) {
                    TaskMap.get(ids).task = task;
                    TaskMap.get(ids).completed = false;
                    return;
                }
                System.err.println("Задачи по данному id не существует.");

            } catch (RuntimeException e) {
                System.err.print("В качестве id введено не число. Повторите команду: ");
            }
        } else {
            System.err.print("Попытка создать пустую задачу повторите ввод: ");
        }
    }

    public static void search(String substring) {
        for (var s : TaskMap.keySet()) {
            if (TaskMap.get(s).task.contains(substring) && TaskMap.get(s).completed == false) {
                System.out.println(String.format("%d. [ ] %s", s, TaskMap.get(s).task));
            }
        }
    }

}
