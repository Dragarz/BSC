public interface Service {
    void add(String task);

    void toggle(String id);

    void print(String task);

    void delete(String id);

    void edit(String command);

    void search(String substring);
}
