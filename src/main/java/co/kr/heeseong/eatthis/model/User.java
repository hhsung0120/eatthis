package co.kr.heeseong.eatthis.model;

import co.kr.heeseong.eatthis.Enum.GenderType;
import co.kr.heeseong.eatthis.Enum.SignUpType;
import co.kr.heeseong.eatthis.Enum.UserStatusType;
import co.kr.heeseong.eatthis.entity.UserDetailEntity;
import co.kr.heeseong.eatthis.entity.UserEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class User {

    private long idx;
    private String id;
    private String password;
    private String nickName;
    private GenderType gender;
    private String birthday;
    private char lunchAlarm;
    private char dinnerAlarm;
    private String lunchAlarmHour;
    private String lunchAlarmMinute;
    private String dinnerAlarmHour;
    private String dinnerAlarmMinute;
    private char eventAlarm;
    private char serviceAlarm;
    @JsonIgnore
    private char termsAgree;
    @JsonIgnore
    private char privacyAgree;
    @JsonIgnore
    private char locationAgree;
    @JsonIgnore
    private SignUpType signUpType;
    private String profileImagePath;
    @JsonIgnore
    private UserStatusType userStatusType;

    public User() {}

    public User(String id, String password) {
        this.id = id;
        this.password = password;
        this.signUpType = SignUpType.DEFAULTE;
        this.termsAgree = 'Y';
        this.privacyAgree = 'Y';
        this.locationAgree = 'Y';
    }

    @Builder
    public User(long idx, String id, String password, String nickName, GenderType gender, String birthday
            , char lunchAlarm, char dinnerAlarm, char eventAlarm, char serviceAlarm, String profileImagePath
            , String lunchAlarmHour, String lunchAlarmMinute, String dinnerAlarmHour, String dinnerAlarmMinute) {
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
        this.lunchAlarmHour = lunchAlarmHour;
        this.lunchAlarmMinute = lunchAlarmMinute;
        this.dinnerAlarmHour = dinnerAlarmHour;
        this.dinnerAlarmMinute = dinnerAlarmMinute;
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
