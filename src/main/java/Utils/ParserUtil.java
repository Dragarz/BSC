package Utils;

import Utils.Constants;
import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;

public class ParserUtil {
    public static String parseCommands(String command){
        Matcher restPartMatcher = Constants.REST_PART_PATTERN.matcher(command);
        return restPartMatcher.replaceAll(StringUtils.EMPTY).trim();
    }
}
