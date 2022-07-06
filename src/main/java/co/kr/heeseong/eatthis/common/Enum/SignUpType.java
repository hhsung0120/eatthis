package co.kr.heeseong.eatthis.common.Enum;

import lombok.Getter;

@Getter
public enum SignUpType {

    DEFAULT("일반")
    , FACEBOOK("페이스북")
    , KAKAO("카카오")
    , GOOGLE("구글")
    , NAVER("네이버")
    ;

    String value = "";
    SignUpType(String value) {
        this.value = value;
    }


}
