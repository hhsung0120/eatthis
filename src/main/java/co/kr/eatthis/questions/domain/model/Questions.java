package co.kr.eatthis.questions.domain.model;

import co.kr.eatthis.common.util.SecretAes;
import co.kr.eatthis.questions.domain.entity.QuestionsEntity;
import lombok.Getter;
import lombok.Setter;

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
    private String answerStatus;
    private String createDate;
    private String lastModifiedDate;

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
}

