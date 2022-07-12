package co.kr.eatthis.questions.domain.entity;


import co.kr.eatthis.common.Enum.QuestionsStatusType;
import co.kr.eatthis.common.domain.entity.TimeAndUserIdEntity;
import co.kr.eatthis.questions.domain.model.Questions;
import co.kr.eatthis.user.domain.entity.UsersEntity;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "questions")
public class QuestionsEntity extends TimeAndUserIdEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;

    private Long categorySeq;

    private Long userSeq;

    private String userName;

    private String phone;

    private String email;

    private String questions;

    private String answer;

    @Enumerated(EnumType.STRING)
    private QuestionsStatusType answerStatus;

    public QuestionsEntity() {
    }

    @Builder(builderClassName = "insertForQuestionsEntity", builderMethodName = "insertForQuestionsEntity")
    public QuestionsEntity(Long categorySeq, Long userSeq, String userName, String phone, String email, String questions) {
        super("system");
        this.categorySeq = categorySeq;
        this.userSeq = userSeq;
        this.userName = userName;
        this.phone = phone;
        this.email = email;
        this.questions = questions;
        this.answerStatus = QuestionsStatusType.WAITING;
    }

//    public Questions toValueObject(String userName) {
//        return Questions.builder()
//                //.createDate(this.getCreateDate())
//                .status(this.getStatus().getValue())
//                //.categoryName(this.getFaqCategoryEntity().getCategoryName())
//                .userName(userName)
//                .phone(this.getPhone())
//                .email(this.getEmail())
//                .questions(this.getQuestions())
//                .answer(this.getAnswer())
//              //  .lastModifiedDate(getLastModifiedDate())
//                .idx(this.getIdx())
//                .userIdx(this.getUserIdx())
//                .categoryIdx(this.getCategoryIdx())
//                .build();
//    }
}
