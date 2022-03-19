package co.kr.heeseong.eatthis.user.domain.model;

import co.kr.heeseong.eatthis.common.Enum.GenderType;
import co.kr.heeseong.eatthis.common.Enum.SignUpType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class AccountUser {

    private Long seq;
    private String userId;
    private String password;
    private String checkPassword;
    private String nickName;
    private GenderType gender;
    private String birthday;
    private String profileImagePath;
    private SignUpType signUpType;

    private String lunchAlarm;
    private String dinnerAlarm;
    private String lunchAlarmHour;
    private String lunchAlarmMinute;
    private String dinnerAlarmHour;
    private String dinnerAlarmMinute;
    private String eventAlarm;
    private String serviceAlarm;

    private String termsAgree;
    private String privacyAgree;
    private String locationAgree;


    public AccountUser() {
    }

    public AccountUser(String id, String password) {
        this.userId = id;
        this.password = password;
        this.signUpType = SignUpType.DEFAULT;
        this.termsAgree = "Y";
        this.privacyAgree = "Y";
        this.locationAgree = "Y";
    }

    @Builder
    public AccountUser(long idx, String id, String password, String nickName, GenderType gender, String birthday
            , String lunchAlarm, String dinnerAlarm, String eventAlarm, String serviceAlarm, String profileImagePath
            , String lunchAlarmHour, String lunchAlarmMinute, String dinnerAlarmHour, String dinnerAlarmMinute) {
        this.seq = idx;
        this.userId = id;
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

//    public UsersEntity toEntity() {
//        return UsersEntity.builder()
//                .idx(idx)
//                .id(id)
//                .password(password)
//                .termsAgree(termsAgree)
//                .privacyAgree(privacyAgree)
//                .locationAgree(locationAgree)
//                .signUpType(signUpType)
//                .build();
//    }

//    public UserDetailEntity toDetailEntity(long idx) {
//        return UserDetailEntity.builder()
//                .idx(idx)
//                .build();
//    }

//    public AccountUser (UsersEntity userEntity) {
//        this.idx = userEntity.getIdx();
//        this.id = userEntity.getId();
//        this.nickName = userEntity.getUserDetailEntity().getNickName();
//        this.password = "";
//        this.gender = userEntity.getUserDetailEntity().getGender();
//        this.birthday = userEntity.getUserDetailEntity().getBirthday();
//        this.lunchAlarm = userEntity.getUserDetailEntity().getLunchAlarm();
//        this.lunchAlarmHour = userEntity.getUserDetailEntity().getLunchAlarmTime().toString().substring(0, 2);
//        this.lunchAlarmMinute = userEntity.getUserDetailEntity().getLunchAlarmTime().toString().substring(3, 5);
//        this.dinnerAlarm = userEntity.getUserDetailEntity().getDinnerAlarm();
//        this.dinnerAlarmHour = userEntity.getUserDetailEntity().getDinnerAlarmTime().toString().substring(0, 2);
//        this.dinnerAlarmMinute = userEntity.getUserDetailEntity().getDinnerAlarmTime().toString().substring(3, 5);
//        this.eventAlarm = userEntity.getUserDetailEntity().getEventAlarm();
//        this.serviceAlarm = userEntity.getUserDetailEntity().getServiceAlarm();
//        this.profileImagePath = userEntity.getUserDetailEntity().getProfileImagePath();
//    }

    @Builder(builderClassName = "signUpOneStepInfo", builderMethodName = "signUpOneStepInfoBuilder")
    public AccountUser(String userId, String password, String checkPassword, String termsAgree, String privacyAgree, String locationAgree) {
        this.userId = userId;
        this.password = password;
        this.checkPassword = checkPassword;
        this.termsAgree = termsAgree;
        this.privacyAgree = privacyAgree;
        this.locationAgree = locationAgree;

    }
}
