package co.kr.heeseong.eatthis.common.domain.model;

import co.kr.heeseong.eatthis.common.util.Jwt;
import co.kr.heeseong.eatthis.user.domain.model.AccountUser;
import lombok.Getter;

import java.util.LinkedHashMap;
import java.util.Map;

@Getter
public class ResponseData {

    private int statusCode;
    private String message;
    private Object data;

    public ResponseData() {
        setResponseData(200, "", null);
    }

    public ResponseData(Exception e) {
        setResponseData(500, "", e);
    }

    public ResponseData(Object data) {
        setResponseData(200, data, null);
    }

    public ResponseData(String key, Object value) {
        Map<String, Object> data = new LinkedHashMap<>();
        data.put(key, value);

        setResponseData(200, data, null);
    }

    public ResponseData(String key, Object value, AccountUser accountUser) {
        Map<String, Object> data = new LinkedHashMap<>();

        data.put(key, value);
        data.put("token", Jwt.createToken(accountUser));

        setResponseData(200, data, null);
    }

    public void setResponseData(int statusCode, Object data, Exception e) {
        Map<String, Object> responseData = new LinkedHashMap<>();
        responseData.put("statusCode", statusCode);
        responseData.put("message", e != null ? e.getMessage() : "성공");
        responseData.put("data", data);

        setResponseDataEncrypt(responseData);
    }

    public void setResponseDataEncrypt(Map<String, Object> responseData) {
        //this.parameter = SecretAes.encrypt(ObjectConverter.mapToJson(responseData));
        //this.parameter = responseData;
        statusCode = Integer.parseInt(responseData.get("statusCode").toString());
        message = responseData.get("message").toString();
        data = responseData.get("data");
    }

}
