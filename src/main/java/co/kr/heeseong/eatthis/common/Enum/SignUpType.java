package co.kr.heeseong.eatthis.common.Enum;

public enum SignUpType {

    DEFAULT("일반"), FACEBOOK("페이스북"), KAKAO("카카오");

    String value = "";

    SignUpType(String value) {
        this.value = value;
    }


}
