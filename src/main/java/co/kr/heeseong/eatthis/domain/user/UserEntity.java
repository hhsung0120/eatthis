package co.kr.heeseong.eatthis.domain.user;


import co.kr.heeseong.eatthis.Enum.SignUpType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "user")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class UserEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Column
    private String id;

    @Column
    private String password;

    @Column
    private String nickName;

    @Enumerated(EnumType.STRING)
    private SignUpType signUpType;

    @OneToOne
    @JoinColumn(name="idx")
    private UserDetailEntity userDetailEntity;

}
