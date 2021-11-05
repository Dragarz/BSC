import Model.Repository;
import Service.TaskService;
import Service.TaskServiceImpl;
import Utils.ParserUtils;
import lombok.extern.slf4j.Slf4j;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Pattern;


@Slf4j
public class ConsoleHandler {
    public static final Pattern ADD_PATTERN = Pattern.compile("add .+");
    public static final Pattern TOGGLE_PATTERN = Pattern.compile("toggle \\d+");
    public static final Pattern PRINT_PATTERN = Pattern.compile("print|print all");
    public static final Pattern DELETE_PATTERN = Pattern.compile("delete \\d+");
    public static final Pattern SEARCH_PATTERN = Pattern.compile("search .+");
    public static final Pattern EDIT_PATTERN = Pattern.compile("edit \\d+ .+");
    public static final String QUIT = "quit";
    public static final String PRINT = "print";

    TaskService service;

    public ConsoleHandler(Repository repository) {
        service = new TaskServiceImpl(repository);
    }

    public void commandHandler() {

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String command = "";

            while (!command.equals(QUIT)) {
                command = reader.readLine().trim();
                log.info("C консоли введена команда {}", command);
                if (command.equals("")) {
                    System.err.println("Строка пуста или состоит из пробелов! Повторите ввод: ");
                    log.error("Строка пуста или состоит из пробелов! {}",command);
                    continue;
                }
                if (command.equals(QUIT)) {
                    System.out.println("Конец программы!");

                } else if (ADD_PATTERN.matcher(command).matches()) {
                    service.add(ParserUtils.isolateRemainder(command));

                } else if (TOGGLE_PATTERN.matcher(command).matches()) {
                    try {
                        service.toggle(ParserUtils.isolateRemainder(command));
                    } catch (IndexOutOfBoundsException e) {
                        System.err.println("Эллемент не найден");
                        log.error("Эллемент не существует ", e);
                    }

                } else if (PRINT_PATTERN.matcher(command).matches()) {
                    if (command.equals(PRINT)) {
                        service.getUnfinishedTask().forEach(System.out::print);
                    } else {
                        service.getAllTasks().forEach(System.out::print);
                    }

                } else if (DELETE_PATTERN.matcher(command).matches()) {
                    try {
                        service.delete(ParserUtils.isolateRemainder(command));
                    } catch (IndexOutOfBoundsException e) {
                        System.err.println("Эллемент не найден");
                        log.error("Эллемент не существует ", e);
                    }
                } else if (EDIT_PATTERN.matcher(command).matches()) {
                    try {
                        String[] withoutCommand = ParserUtils.isolateRemainder(command).split(" ");
                        service.edit(withoutCommand[0], withoutCommand[1]);

                    } catch (IndexOutOfBoundsException e) {
                        System.err.println("Эллемент не найден");
                        log.error("Эллемент не существует ", e);
                    }
                } else if (SEARCH_PATTERN.matcher(command).matches()) {

                    service.searchTasks(ParserUtils.isolateRemainder(command))
                            .forEach(System.out::print);

                } else {
                    System.err.println("Введена не корректная команда " + command);
                    log.debug("Введена не корректная команда {}",command);
                }
            }

        } catch (IOException ignored) {
            log.error("Критическая ошибка!!", ignored);
        }
    }


}
