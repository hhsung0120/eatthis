package co.kr.eatthis.questions.domain.model;

import co.kr.eatthis.common.Enum.QuestionsStatusType;
import co.kr.eatthis.common.util.SecretAes;
import co.kr.eatthis.common.util.StringUtils;
import co.kr.eatthis.questions.domain.entity.QuestionsEntity;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
public class Questions {

    private Long seq;

    @Setter
    private Long userSeq;

    private Long categorySeq;
    private String userName;
    private String phone;
    private String email;
    private String questions;
    private String answer;
    private QuestionsStatusType answerStatus;
    private LocalDateTime createdDatetime;
    private LocalDateTime modifiedDatetime;

    public Questions() {
    }

    public QuestionsEntity toEntity() throws Exception {
        return QuestionsEntity.insertForQuestionsEntity()
                .userSeq(userSeq)
                .categorySeq(categorySeq)
                .userName(SecretAes.encrypt(userName))
                .phone(SecretAes.encrypt(phone.replace("-", "")))
                .email(SecretAes.encrypt(email))
                .questions(questions)
                .build();
    }

    public String getUserName() throws Exception{
        return SecretAes.decrypt(userName);
    }

    public String getPhone() throws Exception{
        return SecretAes.decrypt(phone);
    }

    public String getEmail() throws Exception{
        return SecretAes.decrypt(email);
    }

    public String getCreatedDatetime(){
        return StringUtils.localDateTimeToString(createdDatetime);
    }

    public String getModifiedDatetime(){
        if(modifiedDatetime != null){
            return StringUtils.localDateTimeToString(modifiedDatetime);
        }
        return "";
    }

    public String getAnswerStatus(){
        return answerStatus.getValue();
    }

    @Override
    public String toString() {
        return "Questions{" +
                "seq=" + seq +
                ", userSeq=" + userSeq +
                ", categorySeq=" + categorySeq +
                ", userName='" + userName + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", questions='" + questions + '\'' +
                ", answer='" + answer + '\'' +
                ", answerStatus='" + answerStatus + '\'' +
                ", createdDatetime=" + createdDatetime +
                ", modifiedDatetime=" + modifiedDatetime +
                '}';
    }
}

