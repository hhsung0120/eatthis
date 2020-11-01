package co.kr.heeseong.eatthis.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User extends UserDetail{

    private Long idx;
    private String id;
    private String password;
    private String nickName;
    private String signUpType;

    @Builder
    public User(Long idx, String id, String nickName, String profileImagePath){
        this.idx = idx;
        this.id = id;
        this.nickName = nickName;
        this.setProfileImagePath(profileImagePath);
    }
}
