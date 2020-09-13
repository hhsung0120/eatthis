package co.kr.heeseong.eatthis.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserDetail {

    private long idx;
    private long userIdx;
    private String nickName;
    private String userEmail;
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

}
