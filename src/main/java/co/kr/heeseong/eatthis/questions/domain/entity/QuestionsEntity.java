package co.kr.heeseong.eatthis.questions.domain.entity;


import co.kr.heeseong.eatthis.common.Enum.QuestionsStatusType;
import co.kr.heeseong.eatthis.common.domain.entity.TimeEntity;
import co.kr.heeseong.eatthis.faq.domain.entity.FaqCategoryEntity;
import co.kr.heeseong.eatthis.questions.domain.model.Questions;
import co.kr.heeseong.eatthis.user.domain.entity.UsersEntity;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "questions")
public class QuestionsEntity extends TimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Column(name = "user_idx")
    private Long userIdx;

    @Column(name = "category_idx")
    private Long categoryIdx;

    @Column
    private String questions;

    @Column
    private String answer;

    @Enumerated(EnumType.STRING)
    private QuestionsStatusType status;

    @Column
    private String phone;

    @Column
    private String email;

    @JoinColumn(name = "category_idx", insertable = false, updatable = false)
    @ManyToOne(cascade = CascadeType.ALL)
    private FaqCategoryEntity faqCategoryEntity;

    @JoinColumn(name = "user_idx", insertable = false, updatable = false)
    @ManyToOne(cascade = CascadeType.ALL)
    private UsersEntity user;

    public QuestionsEntity() {
    }

    @Builder
    public QuestionsEntity(Long idx, Long userIdx, String questions, String phone, String email, Long categoryIdx) {
        this.idx = idx;
        this.userIdx = userIdx;
        this.questions = questions;
        this.answer = "";
        this.status = QuestionsStatusType.WAITING;
        this.phone = phone;
        this.email = email;
        this.categoryIdx = categoryIdx;
    }

    public Questions toValueObject(String userName) {
        return Questions.builder()
                .createDate(this.getCreateDate())
                .status(this.getStatus().getValue())
                .categoryName(this.getFaqCategoryEntity().getCategoryName())
                .userName(userName)
                .phone(this.getPhone())
                .email(this.getEmail())
                .questions(this.getQuestions())
                .answer(this.getAnswer())
                .lastModifiedDate(getLastModifiedDate())
                .idx(this.getIdx())
                .userIdx(this.getUserIdx())
                .categoryIdx(this.getCategoryIdx())
                .build();
    }
}
