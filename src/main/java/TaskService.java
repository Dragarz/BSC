public class TaskService {
    //Внесение новых изменений
    private static TaskService taskService = null;
    private int id = 1;
    private String task = null;
    private boolean completed = false;

    public static TaskService getTaskService(){
        if(taskService == null){
            taskService = new TaskService();
        }
        return taskService;
    }

    private TaskService(){

    }

    public void add(String task){
        taskService.task = task;
        taskService.completed = false;
    }

    public void toggle(int id) {

        taskService.completed = taskService.completed ? false : true;
    }

    public void print(){
        if(taskService.completed != true && taskService.task != null){
            System.out.println(String.format("%d. [не выполнена] %s", taskService.id, taskService.task));
        }
    }
    public void printAll(){
        if(taskService.task != null) {
            System.out.println(String.format("%d. [%s] %s", taskService.id, taskService.completed == true ? "выполнена" : "не выполнена", taskService.task));
        }
    }

}
