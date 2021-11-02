import Entities.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

public class Controller {
    private static final Logger logger = LoggerFactory.getLogger(Controller.class);
    public void commandControl(){
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))){
            String command = "";
            Service service = new TaskService();
            Map<String, Task> result;

            while (!command.equals(Constants.QUIT)) {
                command = reader.readLine().trim();
                logger.debug("Введённая команда: " + command);
                if (command.equals("")) {
                    System.err.println("Строка пуста или состоит из пробелов! Повторите ввод: ");
                    continue;
                }
                if (command.equals(Constants.QUIT)) {
                    System.out.println("Конец программы!");

                } else if (Constants.ADD_PATTERN.matcher(command).matches()) {
                    service.add(Parser.ParseCommands(command));
                } else if (Constants.TOGGLE_PATTERN.matcher(command).matches()) {
                    try {
                        service.toggle(Parser.ParseCommands(command));
                    }catch (NullPointerException e){
                        System.err.println("Задачи по данному id не существует.");
                    }
                } else if (Constants.PRINT_PATTERN.matcher(command).matches()) {
                    try {
                        result = service.print(command);
                        result.forEach((key, value) -> System.out.printf("%s. %s %s%n", key, value.getCompleted() ? "[X]" : "[ ]", value.getTask()));
                    }catch (NullPointerException e){
                        System.err.println("Список пуст");
                    }
                } else if (Constants.DELETE_PATTERN.matcher(command).matches()) {
                    try {
                        service.delete(Parser.ParseCommands(command));
                    }catch (NullPointerException e){
                        System.err.println("Задачи по данному id не существует.");
                    }
                } else if (Constants.EDIT_PATTERN.matcher(command).matches()) {
                    try {
                        service.edit(Parser.ParseCommands(command));
                    }catch (NullPointerException e){
                        System.err.println("Задачи по данному id не существует.");
                    }
                } else if (Constants.SEARCH_PATTERN.matcher(command).matches()) {
                    result = service.search(Parser.ParseCommands(command));
                    result.entrySet().forEach(a -> System.out.printf("%s. [ ] %s%n", a.getKey(), a.getValue().getTask()));

                } else {
                    System.err.println("Команда не найдена повторите ввод: ");
                    logger.error("Не корректный ввод! " + command);
                }
            }

        }catch (IOException e){

        }
    }



}
