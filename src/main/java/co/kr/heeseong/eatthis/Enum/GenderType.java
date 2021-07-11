package co.kr.heeseong.eatthis.Enum;

public enum GenderType {

      MALE("남성")
    , FEMALE("여성")
    ;

    String value = "";
    GenderType(String value){
        this.value = value;
    }

    public String getValue(){
        return this.value;
    }

    public static GenderType getGenderTypeToEnum(String gender){
        GenderType[] values = GenderType.values();
        for(GenderType value : values){
            if(value.getValue().equals(gender)){
                return value;
            }
        }
        throw new IllegalArgumentException("유효하지 않은 성별입니다.");
    }
}
