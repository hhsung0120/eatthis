package co.kr.heeseong.eatthis.Enum;

public enum QuestionsStatus {

      WAITING("답변대기")
    , COMPLETE("답변완료")
    ;

    String value = "";
    QuestionsStatus(String value){
        this.value = value;
    }

    public String getValue(){
        return this.value;
    }



}
