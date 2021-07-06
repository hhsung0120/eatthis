package co.kr.heeseong.eatthis.Enum;

public enum QuestionsStatusType {

      WAITING("답변대기")
    , COMPLETE("답변완료")
    ;

    String value = "";
    QuestionsStatusType(String value){
        this.value = value;
    }

    public String getValue(){
        return this.value;
    }



}
