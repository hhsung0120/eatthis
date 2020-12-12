package co.kr.heeseong.eatthis.model;

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
    private String categoryName;
    private String questions;
    private String answer;
    private String status;
    private LocalDateTime createDate;
    private LocalDateTime lastModifiedDate;


}

