package Service;

import Model.Task;

import java.util.Map;

public interface Service {
    void add(String task);

    void toggle(String id);

    void delete(String id);

    void edit(String command);

    Map<String, Task> getTasks(String command);
}