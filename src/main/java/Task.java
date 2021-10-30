public interface Task {
    void setTaskName(String taskName);
    void setCompleted(boolean meaning);
    void switchCompleted();
    String getTaskName();
    boolean getCompleted();

}
