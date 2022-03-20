package co.kr.heeseong.eatthis.common.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

@Slf4j
public class LogUtils {

    public static void errorLog(String description, String requestData, Exception exception) {
        log.error("===========================================");
        if(StringUtils.hasText(description)) log.error("description : {}", description);
        if(StringUtils.hasText(requestData)) log.error("requestData : {}", requestData);
        if(exception != null) log.error("exception message : {}", exception.getMessage());
        log.error("===========================================");
    }

    public static void errorLog(String description, String requestData) {
        errorLog(description, requestData, null);
    }

    public static void errorLog(String description) {
        errorLog(description, null, null);
    }

}
