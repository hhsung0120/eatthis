package co.kr.heeseong.eatthis.dto;

import lombok.Data;

@Data
public class User {

    private Long idx;
    private String id;
    private String password;
    private String nickName;
    private String signUpType;
    private UserDetail userDetail;

}
