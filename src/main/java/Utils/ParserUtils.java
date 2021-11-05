package Utils;

import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@UtilityClass
public class ParserUtils {
    public static String isolateRemainder(String command){
        Matcher restPartMatcher = Pattern.compile("^\\S+(?!\\S)|^.*$").matcher(command);
        return restPartMatcher.replaceAll(StringUtils.EMPTY).trim();
    }
}
