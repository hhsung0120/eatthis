package co.kr.heeseong.eatthis.user.domain.entity;


import co.kr.heeseong.eatthis.common.Enum.GenderType;
import co.kr.heeseong.eatthis.common.Enum.SignUpType;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Map;


@Getter
@Entity
@Table(name = "users")
public class UsersEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;

    private String userId;

    private String password;

    private String nickName;

    @Enumerated(EnumType.STRING)
    private GenderType gender;

    private LocalDate birthday;

    private String profileImagePath;

    @Enumerated(EnumType.STRING)
    private SignUpType signUpType;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_seq", nullable = false)
    private UserDetailEntity userDetailEntity;

    public UsersEntity() {
    }

    @Builder(builderClassName = "byInsertForUsersEntity", builderMethodName = "byInsertForUsersEntity")
    public UsersEntity(String userId, String password, Map<String, String> agreeMap) {
        this.userId = userId;
        this.password = password;
        this.signUpType = SignUpType.DEFAULT;
        this.userDetailEntity = new UserDetailEntity(agreeMap);
    }
}
