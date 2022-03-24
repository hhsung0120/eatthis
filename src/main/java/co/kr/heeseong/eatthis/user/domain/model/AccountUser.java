package co.kr.heeseong.eatthis.user.domain.model;

import co.kr.heeseong.eatthis.common.Enum.GenderType;
import co.kr.heeseong.eatthis.common.Enum.SignUpType;
import co.kr.heeseong.eatthis.user.domain.entity.UsersEntity;
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

    public UsersEntity toEntity() {
        return UsersEntity.byInsertForUsersEntity()
                .userId(userId)
                .password(password)
                .build();
    }

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
