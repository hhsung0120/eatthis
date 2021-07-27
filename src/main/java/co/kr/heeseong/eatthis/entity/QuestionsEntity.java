package co.kr.heeseong.eatthis.entity;


import co.kr.heeseong.eatthis.Enum.QuestionsStatusType;
import co.kr.heeseong.eatthis.entity.common.TimeEntity;
import co.kr.heeseong.eatthis.model.User;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Getter
@ToString
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

    @JoinColumn(name="category_idx", insertable = false, updatable = false)
    @ManyToOne(cascade = CascadeType.ALL)
    private FaqCategoryEntity faqCategoryEntity;

    @JoinColumn(name="user_idx", insertable = false, updatable = false)
    @ManyToOne(cascade = CascadeType.ALL)
    private UserEntity user;

    public QuestionsEntity() {
    }

    public String getLastModifiedDateToString(LocalDateTime lastModifiedDateToString) {
        return lastModifiedDateToString == null ? "" : lastModifiedDateToString.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
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
        this.categoryIdx= categoryIdx;
    }
}
