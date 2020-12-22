package co.kr.heeseong.eatthis.model;

import co.kr.heeseong.eatthis.service.entity.QuestionsEntity;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Questions {
    private long idx;
    private long userIdx;
    private long categoryIdx;
    private String categoryName;
    private String questions;
    private String answer;
    private String status;
    private String phone;
    private String email;
    private LocalDateTime createDate;
    private LocalDateTime lastModifiedDate;

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

