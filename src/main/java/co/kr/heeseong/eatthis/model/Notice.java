package co.kr.heeseong.eatthis.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Notice {

    private long idx;
    private String userIdx;
    private String title;
    private LocalDateTime regDate;

}
