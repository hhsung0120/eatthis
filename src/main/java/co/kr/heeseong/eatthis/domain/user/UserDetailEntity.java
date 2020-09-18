package co.kr.heeseong.eatthis.domain.user;


import co.kr.heeseong.eatthis.domain.common.TimeEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "user_detail")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserDetailEntity extends TimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userIdx;

    @Column
    private String nickName;

    @Column
    private String userEmail;

    @Column
    private String gender;

    @Column
    private String birthday;

    @Column
    private String ci;

    @Column
    private char agree1;

    @Column
    private char agree2;

    @Column
    private char agree3;

    @Column
    private char foodAlarm;

    @Column
    private char eventAlram;

    @Column
    private char serviceAlarm;

    @Column
    private String profileImagePath;
}
