package co.kr.heeseong.eatthis.common.domain.model;

import co.kr.heeseong.eatthis.common.util.ObjectConverter;
import co.kr.heeseong.eatthis.common.util.SecretAes;
import lombok.Getter;

import java.util.LinkedHashMap;
import java.util.Map;

@Getter
public class ResponseData {

    private Object parameter;

    public ResponseData() {
    }

    public ResponseData(Exception e) throws Exception {
        Map<String, Object> responseData = new LinkedHashMap<>();
        responseData.put("statusCode", 500);
        responseData.put("message", e.getMessage());
        responseData.put("data", "");
        setResponseDataEncrypt(responseData);
    }

    public ResponseData(Object data) throws Exception {
        Map<String, Object> responseData = new LinkedHashMap<>();
        responseData.put("statusCode", 200);
        responseData.put("message", "성공");
        responseData.put("data", data);
        setResponseDataEncrypt(responseData);
    }

    public ResponseData(String key, Object value) throws Exception {
        Map<String, Object> responseData = new LinkedHashMap<>();
        responseData.put("statusCode", 200);
        responseData.put("message", "성공");

        Map<String, Object> data = new LinkedHashMap<>();
        data.put(key, value);
        responseData.put("data", data);

        setResponseDataEncrypt(responseData);
    }

    public ResponseData(String key, Object value, String token) throws Exception{
        Map<String, Object> responseData = new LinkedHashMap<>();
        responseData.put("statusCode", 200);
        responseData.put("message", "성공");

        Map<String, Object> data = new LinkedHashMap<>();
        data.put(key, value);
        data.put("token", token);

        responseData.put("data", data);
        setResponseDataEncrypt(responseData);
    }

    public void setResponseDataEncrypt(Map<String, Object> responseData) throws Exception {
        //this.parameter = SecretAes.encrypt(ObjectConverter.mapToJson(responseData));
        this.parameter = responseData;
    }

}
