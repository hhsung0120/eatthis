package co.kr.heeseong.eatthis.common.service;

import co.kr.heeseong.eatthis.common.util.LogUtils;
import co.kr.heeseong.eatthis.common.util.ObjectConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@Service
public class ValidationService {

    public <T> T validation(Map<String, Object> data, Class<T> returnTypeClass) {
//        String jsonText;
//        try {
//            jsonText = SecretAes.decrypt(data.getParameter());
//        } catch (Exception e) {
//            LogUtils.errorLog("decrypt exception", "data", data.getParameter(), e);
//            throw new IllegalArgumentException("decrypt exception");
//        }

        try {
            Map<String, Object> jsonMap = ObjectConverter.objectToMap(data);
            return ObjectConverter.mapToObject(jsonMap, returnTypeClass);
        } catch (Exception e) {
            LogUtils.errorLog("ObjectConverter exception, invalid parameter", "data", data.toString(), e);
            throw new IllegalArgumentException("ObjectConverter exception");
        }
    }
}
