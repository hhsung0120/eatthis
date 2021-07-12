package co.kr.heeseong.eatthis.entity;


import co.kr.heeseong.eatthis.Enum.GenderType;
import co.kr.heeseong.eatthis.Enum.UserStatusType;
import co.kr.heeseong.eatthis.entity.common.TimeEntity;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalTime;

@Entity
@Getter
@Table(name = "user_detail")
public class UserDetailEntity extends TimeEntity {

    @Id
    private long idx;

    @Column
    private String profileImagePath;

    @Column
    private String nickName;

    @Enumerated(EnumType.STRING)
    private GenderType gender;

    private String birthday;

    @Column
    private char lunchAlarm;

    @Column
    private char dinnerAlarm;

    @Column
    private char eventAlarm;

    @Column
    private char serviceAlarm;

    @Column
    private LocalTime lunchAlarmTime;

    @Column
    private LocalTime dinnerAlarmTime;

    @Column(name = "user_status")
    @Enumerated(EnumType.STRING)
    private UserStatusType userStatusType;

    public UserDetailEntity() {}

    public void update(String profileImagePath, String nickName, String birthday, GenderType gender){
        this.profileImagePath = profileImagePath;
        this.nickName = nickName;
        this.gender = gender;
        this.birthday = birthday;
        this.lunchAlarm = 'Y';
        this.dinnerAlarm = 'Y';
        this.eventAlarm = 'Y';
        this.serviceAlarm = 'Y';
        this.lunchAlarmTime = LocalTime.of(12, 0, 0);
        this.dinnerAlarmTime = LocalTime.of(18, 0, 0);
        this.userStatusType = UserStatusType.NORMAL;
    }

    public void updateLunchAlarm(char lunchAlarm, LocalTime alarmTime){
        this.lunchAlarm = lunchAlarm;
        if("Y".equals(String.valueOf(this.lunchAlarm))){
            this.lunchAlarmTime = alarmTime;
        }
    }

    public void updateDinnerAlarm(char dinnerAlarm, LocalTime alarmTime){
        this.dinnerAlarm = dinnerAlarm;
        if("Y".equals(String.valueOf( this.dinnerAlarm))){
            this.dinnerAlarmTime = alarmTime;
        }
    }

    public void updateEventAlarm(char eventAlarm){
        this.eventAlarm = eventAlarm;
    }

    public void updateServiceAlarm(char serviceAlarm){
        this.serviceAlarm = serviceAlarm;
    }

    public void updateStatus(UserStatusType userStatusType){
        this.userStatusType = userStatusType;
    }

    @Builder
    public UserDetailEntity(long idx){
        this.idx = idx;
    }
}

