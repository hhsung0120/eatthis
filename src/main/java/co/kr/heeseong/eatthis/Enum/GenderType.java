package co.kr.heeseong.eatthis.Enum;

public enum GenderType {

      MALE("남성")
    , FEMALE("남성")
    ;

    String value = "";
    GenderType(String value){
        this.value = value;
    }

    public String getValue(){
        return this.value;
    }
}
