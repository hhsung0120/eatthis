package co.kr.heeseong.eatthis.domain.user;


import co.kr.heeseong.eatthis.Enum.GenderType;
import co.kr.heeseong.eatthis.domain.common.TimeEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "user_detail")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserDetailEntity extends TimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Column
    private String nickName;

    @Enumerated(EnumType.STRING)
    private GenderType gender;

    @Column
    private String birthday;

    @Column
    private char foodAlarm;

    @Column
    private char eventAlarm;

    @Column
    private char serviceAlarm;

    @Column
    private String profileImagePath;
}
