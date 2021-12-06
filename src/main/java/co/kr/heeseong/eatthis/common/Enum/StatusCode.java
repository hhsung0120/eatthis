package co.kr.heeseong.eatthis.common.Enum;

public enum StatusCode {

    OK(200, "OK")
    , BAD_REQUEST(400, "BAD_REQUEST")
    , SERVER_ERROR(500, "SERVER_ERROR")
    ;

    private int statusCode;
    private String message;

    StatusCode(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }

    public int getValue(){
        return this.statusCode;
    }

    public String getMessage(){
        return this.message;
    }
}
