
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Main {

    static final Pattern ADD_PATTERN = Pattern.compile("add .+");
    static final Pattern TOGGLE_PATTERN = Pattern.compile("toggle \\d+");
    static final Pattern PRINT_PATTERN = Pattern.compile("print|print all");
    static final Pattern DELETE_PATTERN = Pattern.compile("delete \\d+");
    static final Pattern SEARCH_PATTERN = Pattern.compile("search .+");
    static final Pattern EDIT_PATTERN = Pattern.compile("edit \\d+ .+");
    static final Pattern REST_PART_PATTERN = Pattern.compile(".+ ");
    static final String QUIT = "quit";


    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String command = "";
            Matcher restPartMatcher;
            while (!command.equals(QUIT)) {
                command = reader.readLine().trim();
                if (command.equals("")) {
                    System.err.println("Строка пуста или состоит из пробелов! Повторите ввод: ");
                    continue;
                }
                restPartMatcher = REST_PART_PATTERN.matcher(command);
                if (command.equals(QUIT)) {
                    System.out.println("Конец программы!");

                } else if (ADD_PATTERN.matcher(command).matches()) {
                    TaskService.add(restPartMatcher.replaceAll(StringUtils.EMPTY));
                } else if (TOGGLE_PATTERN.matcher(command).matches()) {
                    TaskService.toggle(restPartMatcher.replaceAll(StringUtils.EMPTY));
                } else if (PRINT_PATTERN.matcher(command).matches()) {
                    TaskService.print(command);
                } else if (DELETE_PATTERN.matcher(command).matches()) {
                    TaskService.delete(restPartMatcher.replaceAll(StringUtils.EMPTY));
                } else if (EDIT_PATTERN.matcher(command).matches()) {
                    TaskService.edit(command);
                } else if (SEARCH_PATTERN.matcher(command).matches()) {
                    TaskService.search(restPartMatcher.replaceAll(StringUtils.EMPTY));
                } else {
                    System.err.println("Команда не найдена повторите ввод: ");
                }
            }
        } catch (IOException ignored) {
        }
    }


}