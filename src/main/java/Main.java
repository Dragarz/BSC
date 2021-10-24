import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.function.Predicate;
import java.util.regex.Pattern;

class Main {

    static final String ADD = "add .+";
    static final String TOGGLE = "toggle \\d+";
    static final String Print = "print all";
    static final String DELETE = "delete \\d+";
    static final String SEARCH = "search .+";
    static final String EDIT = "edit \\d+ .+";
    static final String QUIT = "quit";
    static String command = "";
    static Predicate<String> validate = str -> Pattern.matches(str, command);


    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            while (!command.equals(QUIT)) {
                command = reader.readLine().trim();
                if (validate.test("")) {
                    System.err.println("Строка пуста или состоит из пробелов! Повторите ввод: ");
                    continue;
                }
                if (validate.test(QUIT)) {
                    System.out.println("Конец программы!");
                    continue;

                } else if (validate.test(ADD)) {
                    TaskService.add(command.substring(4).trim());
                } else if (validate.test(TOGGLE)) {
                    TaskService.toggle(command.substring(7).trim());
                } else if (validate.test(Print) || validate.test("print")) {
                    TaskService.print(command);
                } else if (validate.test(DELETE)) {
                    TaskService.delete(command.substring(7).trim());
                } else if (validate.test(EDIT)) {
                    TaskService.edit(command);
                } else if (validate.test(SEARCH)) {
                    TaskService.search(command.substring(7).trim());
                } else {
                    System.err.println("Команда не найдена повторите ввод: ");
                }
            }
        } catch (IOException ignored) {
        }
    }


}