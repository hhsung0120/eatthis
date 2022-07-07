package co.kr.eatthis.user.domain.entity;


import co.kr.eatthis.common.Enum.GenderType;
import co.kr.eatthis.common.Enum.SignUpType;
import co.kr.eatthis.common.Enum.UserStatusType;
import co.kr.eatthis.common.domain.entity.TimeAndUserIdEntity;
import co.kr.eatthis.user.domain.model.AccountUser;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


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

    @Builder(builderClassName = "insertForUsersEntity", builderMethodName = "insertForUsersEntity")
    public UsersEntity(String userId, String password, String nickName, GenderType gender, LocalDate birthday
                    , String profileImagePath, SignUpType signUpType) {
        super("system");
        this.userId = userId;
        this.password = password;
        this.nickName = nickName;
        this.gender = gender;
        this.birthday = birthday;
        this.profileImagePath = profileImagePath;
        this.signUpType = signUpType;
    }

    public void updateUserInfo(AccountUser user) {
        this.nickName = user.getNickName();
        this.gender = user.getGender();
        this.birthday = LocalDate.parse(user.getBirthday(), DateTimeFormatter.ISO_DATE);
        this.userDetailEntity.updateUserStatusType(UserStatusType.NORMAL);
        setModifiedId("system");
    }

    @Override
    public String toString() {
        return "UsersEntity{" + "seq=" + seq + ", userId='" + userId + '\'' + ", password='" + password + '\'' + ", nickName='" + nickName + '\'' + ", gender=" + gender + ", birthday=" + birthday + ", profileImagePath='" + profileImagePath + '\'' + ", signUpType=" + signUpType + ", userDetailEntity=" + userDetailEntity + '}';
    }
}
