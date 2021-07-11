package co.kr.heeseong.eatthis.model;

import lombok.Getter;

@Getter
public class ResponseData {

    private int statusCode;
    private String message;
    private Object data;

    public ResponseData() {
    }

    public ResponseData(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
        this.data = "";
    }

    public ResponseData(int statusCode, String message, Object data) {
        this.statusCode = statusCode;
        this.message = message;
        this.data = data;
    }


}
