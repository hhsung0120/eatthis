package co.kr.heeseong.eatthis.common.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

@Slf4j
public class LogUtils {

    public static void errorLog(String description
            , String message1, Object requestData1
            , String message2, Object requestData2
            , Exception exception) {
        log.error("===========================================");
        if (StringUtils.hasText(description)) log.error("description : [{}]", description);
        if (StringUtils.hasText(message1)) log.error(message1 + " : [{}]", requestData1);
        if (StringUtils.hasText(message2)) log.error(message2 + " : [{}]", requestData2);
        if (StringUtils.hasText(message2)) log.error(message2 + " : [{}]", requestData2);
        if (exception != null) log.error("exception message : [{}]", exception.getMessage());
        log.error("===========================================");
    }

    public static void errorLog(String description, String message1, Object requestData1, String message2, Object requestData2) {
        errorLog(description, message1, requestData1, message2, requestData2, null);
    }

    public static void errorLog(String description, String message1, Object requestData1, Exception e) {
        errorLog(description, message1, requestData1, null, null, e);
    }


    public static void errorLog(String description, String message1, Object requestData1) {
        errorLog(description, message1, requestData1, null, null, null);
    }

    public static void errorLog(String description, Exception e) {
        errorLog(description, null, null, null, null, e);
    }

    public static void errorLog(String description) {
        errorLog(description, null, null, null, null, null);
    }

}
