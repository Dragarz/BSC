package Model;

public class Task {
    private String task;
    private boolean completed;
    private String id = "1";
    public Task(String task, boolean completed){
        this.task = task;
        this.completed = completed;
    }
    public String getTask(){
        return task;
    }

    public boolean getCompleted(){
        return completed;
    }

    public void setTask(String task){
        this.task = task;
    }

    public void setCompleted(boolean completed){
        this.completed = completed;
    }

    public void setId(String id){
        this.id = id;
    }
    public String getId(){
        return id;
    }

    public String toString(){
        return String.format("%s. %s %s%n", id, completed ? "[X]" : "[ ]", task);
    }



}
