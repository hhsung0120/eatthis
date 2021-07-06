package co.kr.heeseong.eatthis.Enum;

public enum UserStatusType {

      NORMAL("정상")
    , STOP("중지")
    , DORMANCY("휴면")
    , SECESSION("탈퇴")
    ;

    String value = "";
    UserStatusType(String value){
        this.value = value;
    }


}
