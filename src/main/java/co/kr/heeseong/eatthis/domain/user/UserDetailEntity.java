package co.kr.heeseong.eatthis.domain.user;


import co.kr.heeseong.eatthis.Enum.GenderType;
import co.kr.heeseong.eatthis.domain.common.TimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "user_detail")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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
    private char foodAlarm;

    @Column
    private char eventAlarm;

    @Column
    private char serviceAlarm;

    public void update(String profileImagePath, String nickName, String birthday, GenderType gender){
        this.profileImagePath = profileImagePath;
        this.nickName = nickName;
        this.gender = gender;
        this.birthday = birthday;
    }

    public void updateFoodAlarm(char foodAlarm){
        this.foodAlarm = foodAlarm;
    }
    public void updateEventAlarm(char eventAlarm){
        this.eventAlarm = eventAlarm;
    }
    public void updateServiceAlarm(char serviceAlarm){
        this.serviceAlarm = serviceAlarm;
    }

    @Builder
    public UserDetailEntity(long idx){
        this.idx = idx;
    }
}
