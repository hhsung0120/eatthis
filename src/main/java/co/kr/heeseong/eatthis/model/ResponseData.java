package co.kr.heeseong.eatthis.model;

import co.kr.heeseong.eatthis.Enum.StatusCode;
import lombok.Getter;

@Getter
public class ResponseData {

    private int statusCode;
    private String message;
    private Object data;

    public ResponseData() {
    }

    public ResponseData(int statusCode, String message, Object data) {
        this.statusCode = statusCode;
        this.message = message;
        this.data = data;
    }
}
