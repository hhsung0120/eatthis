package co.kr.eatthis.common.Enum;

import lombok.Getter;

@Getter
public enum AlamCode {
    LUNCH("점심 추천")
    , DINNER("저녁 추천")
    , EVENT("이벤트 안내")
    , SERVICE("공지사항")
    ;

    String value;
    AlamCode(String value) {
        this.value = value;
    }
}
