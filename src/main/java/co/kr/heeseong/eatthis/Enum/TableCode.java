package co.kr.heeseong.eatthis.Enum;

public enum TableCode {

      USER("회원테이블")
    , REVIEW("리뷰테이블")
    ;

    String value = "";
    TableCode(String value){
        this.value = value;
    }

    public String getValue(){
        return this.value;
    }
}
