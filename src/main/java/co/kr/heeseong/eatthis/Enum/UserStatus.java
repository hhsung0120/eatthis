package co.kr.heeseong.eatthis.Enum;

public enum UserStatus {

      NORMAL("정상")
    , STOP("중지")
    , DORMANCY("휴면")
    , SECESSION("탈퇴")
    ;

    String value = "";
    UserStatus(String value){
        this.value = value;
    }


}
