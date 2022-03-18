package co.kr.heeseong.eatthis.user.domain.entity;


import co.kr.heeseong.eatthis.common.Enum.GenderType;
import co.kr.heeseong.eatthis.common.Enum.SignUpType;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDate;


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

    public UsersEntity() {
    }

    @Builder(builderClassName = "byInsertForUsersEntity", builderMethodName = "byInsertForUsersEntity")
    public UsersEntity(Long seq, String userId, String password, String nickName, GenderType gender, LocalDate birthday, String profileImagePath, SignUpType signUpType) {
        this.seq = seq;
        this.userId = userId;
        this.password = password;
        this.nickName = nickName;
        this.gender = gender;
        this.birthday = birthday;
        this.profileImagePath = profileImagePath;
        this.signUpType = signUpType;
    }
}
