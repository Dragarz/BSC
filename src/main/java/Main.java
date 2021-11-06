import Model.Repository;
import Model.TaskRepository;

public class Main {
    public static void main(String[] args) {
        Repository repository = new TaskRepository();
        ConsoleHandler controller = new ConsoleHandler(repository);
        controller.commandHandler();
    }
}
