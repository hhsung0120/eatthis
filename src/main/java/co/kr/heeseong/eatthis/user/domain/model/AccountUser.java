package co.kr.heeseong.eatthis.user.domain.model;

import co.kr.heeseong.eatthis.common.Enum.GenderType;
import co.kr.heeseong.eatthis.common.Enum.SignUpType;
import co.kr.heeseong.eatthis.common.util.SecretSha;
import co.kr.heeseong.eatthis.user.domain.entity.UserDetailEntity;
import co.kr.heeseong.eatthis.user.domain.entity.UsersEntity;
import lombok.Getter;

import java.util.Map;

@Getter
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
    private Map<String, String> agreeMap;

    public AccountUser() {
    }

    public void setUserid(String userId) {
        //개발 완료 후 지우기
        this.userId = userId + ((int) (Math.random() * 100));
    }

    public UsersEntity toUsersEntity() {
        return UsersEntity.byInsertForUsersEntity()
                .userId(userId)
                .password(SecretSha.encryptPassword(password))
                .build();
    }

    public UserDetailEntity toUserDetailEntity(Long userSeq) {
        return UserDetailEntity.byInsertForUserDetailEntity()
                .userSeq(userSeq)
                .agreeMap(agreeMap)
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
                "seq=" + seq +
                ", userId='" + userId + '\'' +
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
