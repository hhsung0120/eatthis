package co.kr.heeseong.eatthis.domain.user;


import co.kr.heeseong.eatthis.Enum.SignUpType;
import co.kr.heeseong.eatthis.domain.common.TimeEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "user")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userIdx;

    @Column
    private String userId;

    @Column
    private String userPassword;

    @Enumerated(EnumType.STRING)
    private SignUpType signUpType;

    @OneToOne
    @JoinColumn(name="userIdx")
    private UserDetailEntity userDetailEntity;

}
