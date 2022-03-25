package co.kr.heeseong.eatthis.user.domain.entity;


import co.kr.heeseong.eatthis.common.Enum.GenderType;
import co.kr.heeseong.eatthis.common.Enum.SignUpType;
import co.kr.heeseong.eatthis.common.domain.entity.TimeAndUserIdEntity;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Map;


@Getter
@Entity
@Table(name = "users")
public class UsersEntity extends TimeAndUserIdEntity {

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

    public UsersEntity() {
    }

    @Builder(builderClassName = "byInsertForUsersEntity", builderMethodName = "byInsertForUsersEntity")
    public UsersEntity(String userId, String password, Map<String, String> agreeMap) {
        super("system");
        this.userId = userId;
        this.password = password;
        this.signUpType = SignUpType.DEFAULT;
        new UserDetailEntity(agreeMap);
    }
}
