package co.kr.eatthis.common.Enum;

public enum CategoryType {

    FAQ("FAQ")
    , QUESTIONS("1:1 문의")
    ;


    String value = "";

    CategoryType(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

}
