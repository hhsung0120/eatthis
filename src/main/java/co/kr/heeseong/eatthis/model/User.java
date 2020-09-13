package co.kr.heeseong.eatthis.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class User {

    private long idx;
    private String userId;
    private String userPassword;
    private String signUpType;
    private LocalDateTime regDate;

}
