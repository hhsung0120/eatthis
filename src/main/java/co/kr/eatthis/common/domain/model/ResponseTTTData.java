package co.kr.eatthis.common.domain.model;

import lombok.Getter;

import java.util.HashMap;

@Getter
public class ResponseTTTData {

    private int statusCode;
    private String message;
    private Object data;

    public ResponseTTTData() {
    }

    public ResponseTTTData(String message) {
        this(500, message, new HashMap<>());
    }

    public ResponseTTTData(int statusCode, String message) {
        this(statusCode, message, new HashMap<>());
    }

    public ResponseTTTData(int statusCode, String message, Object data) {
        this.statusCode = statusCode;
        this.message = message;
        this.data = data;
    }


}
