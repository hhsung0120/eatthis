package co.kr.heeseong.eatthis.user.domain.entity;


import co.kr.heeseong.eatthis.common.Enum.UserStatusType;
import co.kr.heeseong.eatthis.common.domain.entity.TimeEntity;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalTime;


@Getter
@Entity
@Table(name = "user_detail")
public class UserDetailEntity extends TimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;

    private Long userSeq;

    private String lunchAlarmUseYn;

    private LocalTime lunchAlarmTime;

    private char dinnerAlarmUseYn;

    private LocalTime dinnerAlarmTime;

    private char eventAlarmUseYn;

    private char serviceAlarmUseYn;

    @Enumerated(EnumType.STRING)
    private UserStatusType userStatusType;

    private char privacyAgree;

    private char termsAgree;

    private char locationAgree;

    public UserDetailEntity() {
    }

//    public void update(String profileImagePath, String nickName, String birthday, GenderType gender) {
//        this.profileImagePath = profileImagePath;
//        this.nickName = nickName;
//        this.gender = gender;
//        this.birthday = birthday;
//        this.lunchAlarm = 'Y';
//        this.dinnerAlarm = 'Y';
//        this.eventAlarm = 'Y';
//        this.serviceAlarm = 'Y';
//        this.lunchAlarmTime = LocalTime.of(12, 0, 0);
//        this.dinnerAlarmTime = LocalTime.of(18, 0, 0);
//        this.userStatusType = UserStatusType.NORMAL;
//    }
//
//    public void updateLunchAlarm(char lunchAlarm, LocalTime alarmTime) {
//        this.lunchAlarm = lunchAlarm;
//        if ("Y".equals(String.valueOf(this.lunchAlarm))) {
//            this.lunchAlarmTime = alarmTime;
//        }
//    }
//
//    public void updateDinnerAlarm(char dinnerAlarm, LocalTime alarmTime) {
//        this.dinnerAlarm = dinnerAlarm;
//        if ("Y".equals(String.valueOf(this.dinnerAlarm))) {
//            this.dinnerAlarmTime = alarmTime;
//        }
//    }
//
//    public void updateEventAlarm(char eventAlarm) {
//        this.eventAlarm = eventAlarm;
//    }
//
//    public void updateServiceAlarm(char serviceAlarm) {
//        this.serviceAlarm = serviceAlarm;
//    }
//
//    public void updateStatus(UserStatusType userStatusType) {
//        this.userStatusType = userStatusType;
//    }
//
//    @Builder
//    public UserDetailEntity(long idx) {
//        this.idx = idx;
//    }
}

