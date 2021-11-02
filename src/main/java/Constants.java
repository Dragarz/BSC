import java.util.regex.Pattern;

public class Constants {
    static final Pattern ADD_PATTERN = Pattern.compile("add .+");
    static final Pattern TOGGLE_PATTERN = Pattern.compile("toggle \\d+");
    static final Pattern PRINT_PATTERN = Pattern.compile("print|print all");
    static final Pattern DELETE_PATTERN = Pattern.compile("delete \\d+");
    static final Pattern SEARCH_PATTERN = Pattern.compile("search .+");
    static final Pattern EDIT_PATTERN = Pattern.compile("edit \\d+ .+");
    static final Pattern REST_PART_PATTERN = Pattern.compile("^\\S+(?!\\S)|^.*$");
    static final String QUIT = "quit";
    static final String PRINT = "print";
}
