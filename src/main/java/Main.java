import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {
    public static final String ADD = "add";
    public static final String TOGGLE = "toggle";
    public static final String EDIT = "edit";
    public static final String DELETE = "delete";
    public static final String SEARCH = "search";
    public static final String PRINT = "print";
    public static final String QUIT = "quit";

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String command = "";
            String[] parse;
            while (!command.equals(QUIT)) {
                command = reader.readLine();
                if (command.equals("") || command.replaceAll(" ", "").length() == 0) {
                    System.err.println("Строка пуста или состоит из пробелов! Повторите ввод: ");
                    continue;
                }
                parse = command.split("\\s+");
                switch (parse[0]) {
                    case ADD:
                        TaskService.add(buildComm(parse[0], parse));
                        break;
                    case DELETE:
                        TaskService.delete(parse);
                        break;
                    case EDIT:
                        TaskService.edit(parse, buildComm(EDIT, parse));
                        break;
                    case SEARCH:
                        TaskService.search(buildComm(ADD, parse));
                        break;
                    case PRINT:
                        TaskService.print(parse);
                        break;
                    case TOGGLE:
                        TaskService.toggle(parse);
                        break;
                    case QUIT:
                        System.err.println("Программа завершена!");
                        break;
                    default:
                        System.err.print("Введена не корректная команда! повторите ввод: ");
                }

            }
        } catch (IOException e) {

        }
    }

    public static String buildComm(String comm, String[] str) {
        StringBuilder builder = new StringBuilder();
        String st;
        switch (comm) {
            case ADD:
                for (int i = 1; i < str.length; i++) {
                    builder.append(str[i]).append(" ");
                }
                st = builder.toString().trim();
                if (st.equals("")) {

                    return null;
                }
                return st;

            case EDIT:
                for (int i = 2; i < str.length; i++) {
                    builder.append(str[i]).append(" ");
                }
                st = builder.toString().trim();
                if (st.equals("")) {

                    return null;
                }
                return st;

        }
        return null;
    }
}