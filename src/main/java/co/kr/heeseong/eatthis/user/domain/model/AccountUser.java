package co.kr.heeseong.eatthis.user.domain.model;

import co.kr.heeseong.eatthis.common.Enum.GenderType;
import co.kr.heeseong.eatthis.common.Enum.SignUpType;
import co.kr.heeseong.eatthis.user.domain.entity.UserDetailEntity;
import co.kr.heeseong.eatthis.user.domain.entity.UserEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class AccountUser {

    private Long idx;
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

    public AccountUser() {
    }

    public AccountUser(String id, String password) {
        this.id = id;
        this.password = password;
        this.signUpType = SignUpType.DEFAULTE;
        this.termsAgree = 'Y';
        this.privacyAgree = 'Y';
        this.locationAgree = 'Y';
    }

    @Builder
    public AccountUser(long idx, String id, String password, String nickName, GenderType gender, String birthday
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

    public UserEntity toEntity() {
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

    public UserDetailEntity toDetailEntity(long idx) {
        return UserDetailEntity.builder()
                .idx(idx)
                .build();
    }

    public AccountUser (UserEntity userEntity) {
        this.idx = userEntity.getIdx();
        this.id = userEntity.getId();
        this.nickName = userEntity.getUserDetailEntity().getNickName();
        this.password = "";
        this.gender = userEntity.getUserDetailEntity().getGender();
        this.birthday = userEntity.getUserDetailEntity().getBirthday();
        this.lunchAlarm = userEntity.getUserDetailEntity().getLunchAlarm();
        this.lunchAlarmHour = userEntity.getUserDetailEntity().getLunchAlarmTime().toString().substring(0, 2);
        this.lunchAlarmMinute = userEntity.getUserDetailEntity().getLunchAlarmTime().toString().substring(3, 5);
        this.dinnerAlarm = userEntity.getUserDetailEntity().getDinnerAlarm();
        this.dinnerAlarmHour = userEntity.getUserDetailEntity().getDinnerAlarmTime().toString().substring(0, 2);
        this.dinnerAlarmMinute = userEntity.getUserDetailEntity().getDinnerAlarmTime().toString().substring(3, 5);
        this.eventAlarm = userEntity.getUserDetailEntity().getEventAlarm();
        this.serviceAlarm = userEntity.getUserDetailEntity().getServiceAlarm();
        this.profileImagePath = userEntity.getUserDetailEntity().getProfileImagePath();
    }

}
