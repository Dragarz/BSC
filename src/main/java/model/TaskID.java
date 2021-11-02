package model;

public class TaskID {
    private static Integer id = 1;
    public static String getID(){
        return Integer.toString(id++);
    }
}
