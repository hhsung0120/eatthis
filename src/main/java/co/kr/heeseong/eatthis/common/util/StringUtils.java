package co.kr.heeseong.eatthis.common.util;

import lombok.extern.slf4j.Slf4j;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public class StringUtils {

    final static String regx = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
    final static Pattern pattern = Pattern.compile(regx);

    public static boolean isNotBlank(String str) {
        return (str != null && !str.isEmpty() && containsText(str));
    }

    private static boolean containsText(CharSequence str) {
        int strLen = str.length();
        for (int i = 0; i < strLen; i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    public static void isEmail(String userId) throws Exception {
        if (!isNotBlank(userId)) {
            LogUtils.errorLog("userId must not be null");
            throw new IllegalAccessException("userId must not be null");
        }

        Matcher matcher = pattern.matcher(userId);
        if (!matcher.matches()) {
            LogUtils.errorLog("not a valid email format");
            throw new IllegalAccessException("not a valid email format");
        }

    }
}