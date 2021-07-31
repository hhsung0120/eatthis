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
    private Long idx;
    private Long userIdx;
    private String userName;
    private Long categoryIdx;
    private String categoryName;
    private String questions;
    private String answer;
    private String status;
    private String phone;
    private String email;
    private String createDate;
    private String lastModifiedDate;

    public Questions() {
    }

    @Builder
    public Questions(Long idx, Long userIdx, String userName, Long categoryIdx, String categoryName, String questions, String answer, String status, String phone, String email, String createDate, String lastModifiedDate) {
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

