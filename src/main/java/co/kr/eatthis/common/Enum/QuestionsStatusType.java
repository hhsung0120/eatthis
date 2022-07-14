package co.kr.eatthis.common.Enum;

public enum QuestionsStatusType {

    WAITING("답변 전"), COMPLETE("답변 완료");

    String value = "";

    QuestionsStatusType(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }


}
