package co.kr.heeseong.eatthis.model;

import lombok.Getter;

import java.util.HashMap;

@Getter
public class ResponseData {

    private int statusCode;
    private String message;
    private Object data;

    public ResponseData() {
    }

    public ResponseData(String message) {
        this(500, message, new HashMap<>());
    }

    public ResponseData(int statusCode, String message) {
        this(statusCode, message, new HashMap<>());
    }

    public ResponseData(int statusCode, String message, Object data) {
        this.statusCode = statusCode;
        this.message = message;
        this.data = data;
    }


}
