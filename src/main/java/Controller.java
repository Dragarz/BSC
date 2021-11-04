import Service.Service;
import Utils.Constants;
import Utils.ParserUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Controller {
    public static final Logger logger = LoggerFactory.getLogger(Controller.class);
    Service service;

    public Controller(Service service) {
        this.service = service;
    }

    public void commandControl() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String command = "";

            while (!command.equals(Constants.QUIT)) {
                command = reader.readLine().trim();

                if (command.equals("")) {
                    System.err.println("Строка пуста или состоит из пробелов! Повторите ввод: ");
                    continue;
                }
                if (command.equals(Constants.QUIT)) {
                    System.out.println("Конец программы!");

                } else if (Constants.ADD_PATTERN.matcher(command).matches()) {
                    service.add(ParserUtil.parseCommands(command).trim());
                } else if (Constants.TOGGLE_PATTERN.matcher(command).matches()) {

                        service.toggle(ParserUtil.parseCommands(command).trim());

                } else if (Constants.PRINT_PATTERN.matcher(command).matches()) {

                    service.getTasks(ParserUtil.parseCommands(command).trim())
                            .forEach((key, value) -> System.out.print(value));

                } else if (Constants.DELETE_PATTERN.matcher(command).matches()) {
                    try {
                        service.delete(ParserUtil.parseCommands(command).trim());
                    }catch (NullPointerException e){
                        System.out.println(e.getMessage());
                    }
                } else if (Constants.EDIT_PATTERN.matcher(command).matches()) {
                    try {
                        service.edit(ParserUtil.parseCommands(command).trim());
                    }catch (NullPointerException e){
                        System.out.println(e.getMessage());
                    }
                } else if (Constants.SEARCH_PATTERN.matcher(command).matches()) {

                    service.getTasks(ParserUtil.parseCommands(command).trim())
                            .forEach((key, value) -> System.out.print(value));

                } else {
                    try{
                        throw new Exception("Введена не корректная команда " + command);
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                        logger.error(e.getMessage());
                    }

                }
            }

        } catch (IOException ignored) {

        }
    }


}
