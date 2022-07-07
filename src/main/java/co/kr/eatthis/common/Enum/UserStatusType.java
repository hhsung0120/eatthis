package co.kr.eatthis.common.Enum;

public enum UserStatusType {

    SIGNING("가입 중")
    , NORMAL("정상")
    , STOP("중지")
    , DORMANCY("휴면")
    , SECESSION("탈퇴")
    ;

    String value = "";

    UserStatusType(String value) {
        this.value = value;
    }


}
