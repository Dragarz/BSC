
import java.util.HashMap;
import java.util.Map;

public class TaskService {

    private static Map<Integer, TaskService> taskSMap = null;
    private static int id = 1;
    private String task = null;
    private Boolean completed = null;




//    private TaskService(String task){
//        if(taskSMap == null){
//            taskSMap = new HashMap<>();
//        }
//        taskSMap.put(id++, this);
//        this.task = task;
//        this.completed = false;
//
//    }
    private TaskService(String task){
        if(taskSMap == null){
            taskSMap = new HashMap<>();
            taskSMap.put(id, this);
        }else {
            taskSMap.replace(id, this);
        }
        this.task = task;
        this.completed = false;

    }

    public static void add(String task){
        new TaskService(task);
    }

    public static void toggle(int id) {
        for(var s : taskSMap.keySet()){
            if(s.equals(id)){
                taskSMap.get(s).completed = !taskSMap.get(s).completed;
                return;
            }
        }
        System.err.println("Задачи с данным id - " + id + "не найдено!");
    }

    public static void print(){
        for(var s : taskSMap.keySet()) {
            if(taskSMap.get(s).completed == false) {
                System.out.println(String.format("%d.   %s", s, taskSMap.get(s).task));
            }
        }


    }
    public static void printAll(){
        for(var s : taskSMap.keySet()){
            System.out.println(String.format("%d. %s %s", s, taskSMap.get(s).completed == true ? "[X]" : "[ ]", taskSMap.get(s).task));
        }
    }

}
