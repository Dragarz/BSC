import Service.Service;
import Utils.Constants;
import Utils.ParserUtil;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Controller {
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
                    try {
                        service.toggle(ParserUtil.parseCommands(command).trim());
                    }catch(NullPointerException e){
                        System.out.println(e.getMessage());
                    }
                } else if (Constants.PRINT_PATTERN.matcher(command).matches()) {
                        service.print(ParserUtil.parseCommands(command).trim());
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
                    service.search(ParserUtil.parseCommands(command).trim());
                } else {
                    System.err.println("Ошибка ввода!");
                }
            }

        } catch (IOException e) {

        }
    }


}
