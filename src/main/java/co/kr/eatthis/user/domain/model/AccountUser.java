package co.kr.eatthis.user.domain.model;

import co.kr.eatthis.common.Enum.GenderType;
import co.kr.eatthis.common.Enum.SignUpType;
import co.kr.eatthis.common.util.SecretSha;
import co.kr.eatthis.user.domain.entity.UserDetailEntity;
import co.kr.eatthis.user.domain.entity.UsersEntity;
import co.kr.eatthis.common.util.StringUtils;
import lombok.Getter;

import java.time.LocalDate;
import java.util.Map;

@Getter
public class AccountUser {

    private Long userSeq;
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
    private Map<String, String> agreeMap;

    public AccountUser() {
    }

    public AccountUser(Long test) {
        this.userSeq = test;
    }

    public UsersEntity toUsersEntity() {
        return UsersEntity.insertForUsersEntity()
                .userId(userId)
                .password(SecretSha.encryptPassword(""))
                .nickName(nickName)
                .gender(gender)
                .birthday(LocalDate.parse(birthday))
                .profileImagePath(profileImagePath)
                .signUpType(signUpType)
                .build();
    }

    public UserDetailEntity toUserDetailEntity(Long userSeq) {
        return UserDetailEntity.insertForUserDetailEntity()
                .userSeq(userSeq)
                .agreeMap(agreeMap)
                .build();
    }
//    public UserDetailEntity toDetailEntity(long idx) {
//        return UserDetailEntity.builder()
//                .idx(idx)
//                .build();
//    }

    public AccountUser(UsersEntity userEntity) {
        this.userSeq = userEntity.getSeq();
        this.userId = userEntity.getUserId();
        this.nickName = userEntity.getNickName();
        this.password = "";
        this.gender = userEntity.getGender();
        this.birthday = StringUtils.localDateToString(userEntity.getBirthday());
        this.lunchAlarm = userEntity.getUserDetailEntity().getLunchAlarmUseYn();
        this.lunchAlarmHour = userEntity.getUserDetailEntity().getLunchAlarmTime().toString().substring(0, 2);
        this.lunchAlarmMinute = userEntity.getUserDetailEntity().getLunchAlarmTime().toString().substring(3, 5);
        this.dinnerAlarm = userEntity.getUserDetailEntity().getDinnerAlarmUseYn();
        this.dinnerAlarmHour = userEntity.getUserDetailEntity().getDinnerAlarmTime().toString().substring(0, 2);
        this.dinnerAlarmMinute = userEntity.getUserDetailEntity().getDinnerAlarmTime().toString().substring(3, 5);
        this.eventAlarm = userEntity.getUserDetailEntity().getEventAlarmUseYn();
        this.serviceAlarm = userEntity.getUserDetailEntity().getServiceAlarmUseYn();
        this.profileImagePath = userEntity.getProfileImagePath();
    }

//    @Builder(builderClassName = "signUpOneStepInfo", builderMethodName = "signUpOneStepInfoBuilder")
//    public AccountUser(String userId, String password, String checkPassword, String termsAgree, String privacyAgree, String locationAgree) {
//        this.userId = userId;
//        this.password = password;
//        this.checkPassword = checkPassword;
//        this.termsAgree = termsAgree;
//        this.privacyAgree = privacyAgree;
//        this.locationAgree = locationAgree;
//    }

    @Override
    public String toString() {
        return "AccountUser{" +
                "userSeq=" + userSeq +
                ", userId='" + userId + '\'' +
                ", password='" + password + '\'' +
                ", checkPassword='" + checkPassword + '\'' +
                ", nickName='" + nickName + '\'' +
                ", gender=" + gender +
                ", birthday='" + birthday + '\'' +
                ", profileImagePath='" + profileImagePath + '\'' +
                ", signUpType=" + signUpType +
                ", lunchAlarm='" + lunchAlarm + '\'' +
                ", dinnerAlarm='" + dinnerAlarm + '\'' +
                ", lunchAlarmHour='" + lunchAlarmHour + '\'' +
                ", lunchAlarmMinute='" + lunchAlarmMinute + '\'' +
                ", dinnerAlarmHour='" + dinnerAlarmHour + '\'' +
                ", dinnerAlarmMinute='" + dinnerAlarmMinute + '\'' +
                ", eventAlarm='" + eventAlarm + '\'' +
                ", serviceAlarm='" + serviceAlarm + '\'' +
                ", agreeMap=" + agreeMap +
                '}';
    }
}
