package co.kr.heeseong.eatthis.common.service;

import co.kr.heeseong.eatthis.common.domain.model.RequestData;
import co.kr.heeseong.eatthis.common.util.LogUtils;
import co.kr.heeseong.eatthis.common.util.ObjectConverter;
import co.kr.heeseong.eatthis.common.util.SecretAes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@Service
public class ValidationService {

    public <T> T validation(RequestData data, Class<T> returnTypeClass) {
        log.info("request parameter : {}", data.getParameter());

        String jsonText;
        try {
            jsonText = SecretAes.decrypt(data.getParameter());
        } catch (Exception e) {
            LogUtils.errorLog("decrypt exception", "data", data.getParameter(), e);
            throw new IllegalArgumentException("decrypt exception");
        }

        try {
            Map<String, Object> jsonMap = ObjectConverter.jsonToMap(jsonText);
            return (T) ObjectConverter.mapToObject(jsonMap, returnTypeClass);
        } catch (Exception e) {
            LogUtils.errorLog("ObjectConverter exception", "data", data.getParameter(), e);
            throw new IllegalArgumentException("ObjectConverter exception");
        }
    }
}
