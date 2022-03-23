package co.kr.heeseong.eatthis.common.util;

import lombok.extern.slf4j.Slf4j;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public class StringUtils {

    static final String regx = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
    static final Pattern pattern = Pattern.compile(regx);

    public static final String MUST_NOT_BE_NULL = "must not be null";
    public static final String NOT_A_VALID_PARAMETER = "not a valid parameter";
    public static final String NOT_A_VALID = "not a valid";

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
            LogUtils.errorLog("userId " + MUST_NOT_BE_NULL);
            throw new IllegalAccessException("userId " + MUST_NOT_BE_NULL);
        }

        Matcher matcher = pattern.matcher(userId);
        if (!matcher.matches()) {
            LogUtils.errorLog(NOT_A_VALID + " email format");
            throw new IllegalAccessException(NOT_A_VALID + " email format");
        }

    }
}