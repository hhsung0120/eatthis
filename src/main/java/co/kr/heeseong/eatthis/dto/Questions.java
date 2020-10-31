package co.kr.heeseong.eatthis.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class Questions {
    private Long idx;
    private String categoryName;
    private String questions;
    private String answer;
    private String status;
    private LocalDateTime createDate;
    private LocalDateTime lastModifiedDate;

    @Builder
    public Questions(Long idx, String categoryName, String questions, String answer, String status, LocalDateTime createDate, LocalDateTime lastModifiedDate){
        this.idx = idx;
        this.categoryName = categoryName;
        this.questions = questions;
        this.answer = answer;
        this.status = status;
        this.createDate = createDate;
        this.lastModifiedDate = lastModifiedDate;
    }

}

