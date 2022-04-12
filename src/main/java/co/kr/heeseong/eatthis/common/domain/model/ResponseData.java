package co.kr.heeseong.eatthis.common.domain.model;

import co.kr.heeseong.eatthis.common.util.Jwt;
import co.kr.heeseong.eatthis.user.domain.model.AccountUser;
import lombok.Getter;

import java.util.LinkedHashMap;
import java.util.Map;

@Getter
public class ResponseData {

    private Object parameter;

    public ResponseData() throws Exception {
        setResponseData(200, "", null);
    }

    public ResponseData(Exception e) throws Exception {
        setResponseData(500, "", e);
    }

    public ResponseData(Object data) throws Exception {
        setResponseData(200, data, null);
    }

    public ResponseData(String key, Object value) throws Exception {
        Map<String, Object> data = new LinkedHashMap<>();
        data.put(key, value);

        setResponseData(200, data, null);
    }

    public ResponseData(String key, Object value, AccountUser accountUser) throws Exception {
        Map<String, Object> data = new LinkedHashMap<>();

        accountUser.setUserSeq((Long)value);
        data.put(key, value);
        data.put("token", Jwt.createToken(accountUser));

        setResponseData(200, data, null);
    }

    public void setResponseData(int statusCode, Object data, Exception e) throws Exception {
        Map<String, Object> responseData = new LinkedHashMap<>();
        responseData.put("statusCode", statusCode);
        responseData.put("message", e != null ? e.getMessage() : "성공");
        responseData.put("data", data);

        setResponseDataEncrypt(responseData);
    }

    public void setResponseDataEncrypt(Map<String, Object> responseData) throws Exception {
        //this.parameter = SecretAes.encrypt(ObjectConverter.mapToJson(responseData));
        this.parameter = responseData;
    }

}
