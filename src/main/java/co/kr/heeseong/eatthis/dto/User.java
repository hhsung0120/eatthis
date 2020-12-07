package co.kr.heeseong.eatthis.dto;

import co.kr.heeseong.eatthis.Enum.SignUpType;
import co.kr.heeseong.eatthis.domain.user.UserDetailEntity;
import co.kr.heeseong.eatthis.domain.user.UserEntity;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.*;

@Builder
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class User {
    private Long idx = 0L;
    private String id;
    private String password;
    private String nickName;
    private String gender;
    private String birthday;
    private char lunchAlarm;
    private char dinnerAlarm;
    private int alarmTimeHour;
    private int alarmTimeMinute;
    private char eventAlarm;
    private char serviceAlarm;
    private char termsAgree;
    private char privacyAgree;
    private char locationAgree;
    private SignUpType signUpType;
    private String profileImagePath;

    public UserEntity toEntity(){
        return UserEntity.builder()
                .idx(idx)
                .id(id)
                .password(password)
                .termsAgree(termsAgree)
                .privacyAgree(privacyAgree)
                .locationAgree(locationAgree)
                .signUpType(signUpType)
                .build();
    }

    public UserDetailEntity toDetailEntity(long idx){
        return UserDetailEntity.builder()
                .idx(idx)
                .build();
    }
}
