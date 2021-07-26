package co.kr.heeseong.eatthis.model;

import co.kr.heeseong.eatthis.entity.QuestionsEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class Questions {
    private long idx;
    private long userIdx;
    private String userName;
    private long categoryIdx;
    private String categoryName;
    private String questions;
    private String answer;
    private String status;
    private String phone;
    private String email;
    private LocalDateTime createDate;
    private LocalDateTime lastModifiedDate;

    public Questions() {
    }

    public Questions(long idx, long userIdx) {
        this.idx = idx;
        this.userIdx = userIdx;
    }

    @Builder
    public Questions(long idx, long userIdx, String userName, long categoryIdx, String categoryName, String questions, String answer, String status, String phone, String email, LocalDateTime createDate, LocalDateTime lastModifiedDate) {
        this.idx = idx;
        this.userIdx = userIdx;
        this.userName = userName;
        this.categoryIdx = categoryIdx;
        this.categoryName = categoryName;
        this.questions = questions;
        this.answer = answer;
        this.status = status;
        this.phone = phone;
        this.email = email;
        this.createDate = createDate;
        this.lastModifiedDate = lastModifiedDate;
    }

    public QuestionsEntity toEntity(){
        return  QuestionsEntity.builder()
                    .userIdx(userIdx)
                    .questions(questions)
                    .phone(phone)
                    .email(email)
                    .categoryIdx(categoryIdx)
                    .build();
    }

}

