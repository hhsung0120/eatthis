package co.kr.heeseong.eatthis.common.Enum;

public enum SignUpType {

      DEFAULTE("일반")
    , FACEBOOK("페이스북")
    , KAKAO("카카오")
    ;

    String value = "";
    SignUpType(String value){
        this.value = value;
    }


}
