package Utlis;

import Utlis.Constants;
import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;

public class Parser {
    public static String ParseCommands(String command){
        Matcher restPartMatcher = Constants.REST_PART_PATTERN.matcher(command);
        return restPartMatcher.replaceAll(StringUtils.EMPTY).trim();
    }
}
