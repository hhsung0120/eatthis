package co.kr.heeseong.eatthis.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserDetail {

    private Long userIdx;
    private String email;
    private String gender;
    private String birthday;
    private String ci;
    private char agree1;
    private char agree2;
    private char agree3;
    private char foodAlarm;
    private char eventAlram;
    private char serviceAlarm;
    private String profileImagePath;
    private LocalDateTime regDate;
    private LocalDateTime modDate;

}
