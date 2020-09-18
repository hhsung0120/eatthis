package co.kr.heeseong.eatthis.dto;

import lombok.Data;

@Data
public class UserDto {

    private Long userIdx;
    private String userId;
    private String userPassword;
    private String signUpType;
    private UserDetailDto userDetail;

    public UserDetailDto getUserDetail(){
        return this.userDetail;
    }

}
