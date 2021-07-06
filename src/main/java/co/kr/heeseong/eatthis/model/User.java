package co.kr.heeseong.eatthis.model;

import co.kr.heeseong.eatthis.Enum.SignUpType;
import co.kr.heeseong.eatthis.Enum.UserStatusType;
import co.kr.heeseong.eatthis.entity.UserDetailEntity;
import co.kr.heeseong.eatthis.entity.UserEntity;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.*;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class User {

    private long idx;
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
    private UserStatusType userStatusType;

    @Builder
    public User(long idx, String id, String password, String nickName, String gender, String birthday, char lunchAlarm, char dinnerAlarm, char eventAlarm, char serviceAlarm, String profileImagePath) {
        this.idx = idx;
        this.id = id;
        this.password = password;
        this.nickName = nickName;
        this.gender = gender;
        this.birthday = birthday;
        this.lunchAlarm = lunchAlarm;
        this.dinnerAlarm = dinnerAlarm;
        this.eventAlarm = eventAlarm;
        this.serviceAlarm = serviceAlarm;
        this.profileImagePath = profileImagePath;
    }

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
