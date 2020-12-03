package co.kr.heeseong.eatthis.Enum;

public enum ErrorCode {

      USER_NOT_FOUNT("존재하지 않는 회원 입니다.")
    , USER_DUPLICATE("중복 된 회원 입니다.")
    , INVALID_ARGUMENT("올바른 인자가 아닙니다.")
    ;

    String value = "";
    ErrorCode(String value){
        this.value = value;
    }

    public String getValue(){
        return this.value;
    }
}
