package co.kr.heeseong.eatthis.user.domain.entity;


import co.kr.heeseong.eatthis.common.Enum.GenderType;
import co.kr.heeseong.eatthis.common.Enum.SignUpType;
import co.kr.heeseong.eatthis.common.domain.entity.TimeAndUserIdEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.Value;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Map;


@Getter
@Entity
@Table(name = "users")
public class UsersEntity extends TimeAndUserIdEntity {

    @Id
    @Column(name = "seq")
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

    @OneToOne(mappedBy = "usersEntity")
    private UserDetailEntity userDetailEntity;

    public UsersEntity() {
    }

    @Builder(builderClassName = "byInsertForUsersEntity", builderMethodName = "byInsertForUsersEntity")
    public UsersEntity(String userId, String password) {
        super("system");
        this.userId = userId;
        this.password = password;
        this.signUpType = SignUpType.DEFAULT;
    }

    @Override
    public String toString() {
        return "UsersEntity{" +
                "seq=" + seq +
                ", userId='" + userId + '\'' +
                ", password='" + password + '\'' +
                ", nickName='" + nickName + '\'' +
                ", gender=" + gender +
                ", birthday=" + birthday +
                ", profileImagePath='" + profileImagePath + '\'' +
                ", signUpType=" + signUpType +
                ", userDetailEntity=" + userDetailEntity +
                '}';
    }
}
