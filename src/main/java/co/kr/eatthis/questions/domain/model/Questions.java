package co.kr.eatthis.questions.domain.model;

import co.kr.eatthis.common.Enum.QuestionsStatusType;
import co.kr.eatthis.common.util.LogUtils;
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

    public Questions(QuestionsEntity questionsEntity) {
        createdDatetime = questionsEntity.getCreatedDatetime();
        answerStatus = questionsEntity.getAnswerStatus();
        categorySeq = questionsEntity.getCategorySeq();
        userName = questionsEntity.getUserName();
        phone = questionsEntity.getPhone();
        email = questionsEntity.getEmail();
        questions = questionsEntity.getQuestions();
        answer = questionsEntity.getAnswer();
        modifiedDatetime = questionsEntity.getModifiedDatetime();
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

    public String getUserName() {
        try {
            return SecretAes.decrypt(userName);
        } catch (Exception e) {
            LogUtils.errorLog("getUserName() exception", "userName", userName);
            return "";
        }
    }

    public String getPhone() {
        try {
            return SecretAes.decrypt(phone);
        } catch (Exception e) {
            LogUtils.errorLog("getPhone() exception", "phone", phone);
            return "";
        }
    }

    public String getEmail() {
        try {
            return SecretAes.decrypt(email);
        } catch (Exception e) {
            LogUtils.errorLog("getEmail() exception", "email", email);
            return "";
        }
    }

    public String getCreatedDatetime() {
        return StringUtils.localDateTimeToString(createdDatetime);
    }

    public String getModifiedDatetime() {
        if (modifiedDatetime != null) {
            return StringUtils.localDateTimeToString(modifiedDatetime);
        }
        return "";
    }

    public String getAnswerStatus() {
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

