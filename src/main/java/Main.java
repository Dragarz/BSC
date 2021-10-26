
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Pattern;

class Main {

    static final Pattern ADD = Pattern.compile("add .+");
    static final Pattern TOGGLE = Pattern.compile("toggle \\d+");
    static final Pattern PRINT = Pattern.compile("print|print all");
    static final Pattern DELETE = Pattern.compile("delete \\d+");
    static final Pattern SEARCH = Pattern.compile("search .+");
    static final Pattern EDIT = Pattern.compile("edit \\d+ .+");
    static final String QUIT = "quit";


    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String command = "";
            int command_part_length;

            while (!command.equals(QUIT)) {
                command = reader.readLine().trim();
                if (command.equals("")) {
                    System.err.println("Строка пуста или состоит из пробелов! Повторите ввод: ");
                    continue;
                }
                command_part_length = command.split(" ")[0].length() + 1;
                if (command.equals(QUIT)) {
                    System.out.println("Конец программы!");

                } else if (ADD.matcher(command).matches()) {
                    TaskService.add(command.substring(command_part_length).trim());
                } else if (TOGGLE.matcher(command).matches()) {
                    TaskService.toggle(command.substring(command_part_length).trim());
                } else if (PRINT.matcher(command).matches()) {
                    TaskService.print(command);
                } else if (DELETE.matcher(command).matches()) {
                    TaskService.delete(command.substring(command_part_length).trim());
                } else if (EDIT.matcher(command).matches()) {
                    TaskService.edit(command);
                } else if (SEARCH.matcher(command).matches()) {
                    TaskService.search(command.substring(command_part_length).trim());
                } else {
                    System.err.println("Команда не найдена повторите ввод: ");
                }
            }
        } catch (IOException ignored) {
        }
    }


}