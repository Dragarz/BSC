import Model.Repository;
import Model.TaskRepository;
import Service.TaskService;

public class Main {
    public static void main(String[] args) {
        Repository repository = new TaskRepository();
        TaskService service = new TaskService(repository);
        Controller controller = new Controller(service);
        controller.commandControl();
    }
}
