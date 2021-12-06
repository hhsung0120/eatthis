package co.kr.heeseong.eatthis.user.domain.entity;


import co.kr.heeseong.eatthis.common.Enum.SignUpType;
import co.kr.heeseong.eatthis.user.domain.model.AccountUser;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;


@Entity
@Getter
@Builder
@AllArgsConstructor
@Table(name = "user")
public class UserEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Column
    private String id;

    @Column
    private String password;

    @Column
    private char termsAgree;

    @Column
    private char privacyAgree;

    @Column
    private char locationAgree;

    @Column(name = "sign_up_type")
    @Enumerated(EnumType.STRING)
    private SignUpType signUpType;

    @OneToOne
    @JoinColumn(name="idx")
    private UserDetailEntity userDetailEntity;

    public UserEntity() {
    }

    public AccountUser toValueObject(){
        return AccountUser.builder()
                .idx(idx)
                .id(id)
                .nickName(userDetailEntity.getNickName())
                .password("")
                .gender(userDetailEntity.getGender())
                .birthday(userDetailEntity.getBirthday())
                .lunchAlarm(userDetailEntity.getLunchAlarm())
                .lunchAlarmHour(userDetailEntity.getLunchAlarmTime().toString().substring(0,2))
                .lunchAlarmMinute(userDetailEntity.getLunchAlarmTime().toString().substring(3,5))
                .dinnerAlarm(userDetailEntity.getDinnerAlarm())
                .dinnerAlarmHour(userDetailEntity.getDinnerAlarmTime().toString().substring(0,2))
                .dinnerAlarmMinute(userDetailEntity.getDinnerAlarmTime().toString().substring(3,5))
                .eventAlarm(userDetailEntity.getEventAlarm())
                .serviceAlarm(userDetailEntity.getServiceAlarm())
                .profileImagePath(userDetailEntity.getProfileImagePath())
                .build();
    }
}
