package Utils;

import java.util.regex.Pattern;

public class Constants {
    public static final Pattern ADD_PATTERN = Pattern.compile("add .+");
    public static final Pattern TOGGLE_PATTERN = Pattern.compile("toggle \\d+");
    public static final Pattern PRINT_PATTERN = Pattern.compile("print|print all");
    public static final Pattern DELETE_PATTERN = Pattern.compile("delete \\d+");
    public static final Pattern SEARCH_PATTERN = Pattern.compile("search .+");
    public static final Pattern EDIT_PATTERN = Pattern.compile("edit \\d+ .+");
    public static final Pattern REST_PART_PATTERN = Pattern.compile("^\\S+(?!\\S)|^.*$");
    public static final String QUIT = "quit";
    public static final String PRINT = "print";
}
