package co.kr.heeseong.eatthis.entity;


import co.kr.heeseong.eatthis.Enum.SignUpType;
import lombok.*;

import javax.persistence.*;


@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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

    @Enumerated(EnumType.STRING)
    private SignUpType signUpType;

    @OneToOne
    @JoinColumn(name="idx")
    private UserDetailEntity userDetailEntity;

}
