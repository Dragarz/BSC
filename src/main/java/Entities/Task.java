package Entities;

public class Task {
    private String task;
    private boolean completed;

    public Task(String task){
        this.task = task;
        completed = false;
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



}
