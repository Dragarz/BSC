package service;

import model.Task;

import java.util.Map;

public interface Service {
    void add(String task);

    void toggle(String id);

    Map print(String task);

    void delete(String id);

    void edit(String command);

    Map<String, Task> search(String substring);
}