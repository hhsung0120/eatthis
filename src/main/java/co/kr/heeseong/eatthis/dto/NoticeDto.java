package co.kr.heeseong.eatthis.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class NoticeDto {
    private long idx;
    private String userIdx;
    private String title;
    private LocalDateTime regDate;
}
