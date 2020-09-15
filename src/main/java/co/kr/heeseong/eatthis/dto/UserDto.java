package co.kr.heeseong.eatthis.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserDto {

    private long idx;
    private String userId;
    private String userPassword;
    private String signUpType;
    private LocalDateTime regDate;
    private UserDetailDto userDetail;

    public UserDetailDto getUserDetail(){
        return this.userDetail;
    }

}
