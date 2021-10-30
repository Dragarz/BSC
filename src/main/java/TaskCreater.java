import javax.swing.*;

public class TaskCreater implements Task{
    private String taskName;
    private boolean completed;

    TaskCreater(String taskName){
        this.taskName = taskName;
        completed = false;
    }
    @Override
    public void setTaskName(String taskName){
        this.taskName = taskName;
    }
    @Override
    public void switchCompleted(){
        completed = !completed;
    }
    @Override
    public void setCompleted(boolean meaning){
        completed = meaning;
    }
    @Override
    public String getTaskName(){
        return taskName;
    }
    @Override
    public boolean getCompleted(){
        return completed;
    }
}
