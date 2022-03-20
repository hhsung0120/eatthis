package co.kr.heeseong.eatthis.common.domain.model;

import co.kr.heeseong.eatthis.common.util.ObjectConverter;
import co.kr.heeseong.eatthis.common.util.SecretAes;
import lombok.Getter;

import java.util.LinkedHashMap;
import java.util.Map;

@Getter
public class ResponseData {

    private String parameter;

    public ResponseData() {
    }

    public ResponseData(Exception e) throws Exception{
        Map<String, Object> responseData = new LinkedHashMap<>();
        responseData.put("statusCode", 500);
        responseData.put("message", e.getMessage());
        responseData.put("data", "");
        this.parameter = SecretAes.encrypt(ObjectConverter.mapToJson(responseData));
    }

}
