package Service;

import Model.Task;

import java.util.Map;

public interface Service {
    void add(String task);

    void toggle(String id);

    void print(String command);

    void delete(String id);

    void edit(String command);

    void search(String substring);

    Map<String, Task> getTasks();
}