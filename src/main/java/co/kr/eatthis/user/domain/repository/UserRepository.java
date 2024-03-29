package co.kr.eatthis.user.domain.repository;

import co.kr.eatthis.common.Enum.SignUpType;
import co.kr.eatthis.user.domain.entity.UsersEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<UsersEntity, Long> {

    @Query("SELECT u FROM UsersEntity u WHERE u.seq = :seq AND u.password = :password")
    UsersEntity findByIdAndPassword(@Param("seq") String seq, @Param("password") String password);

    @Query("SELECT u FROM UsersEntity u WHERE u.userId = :userId")
    UsersEntity findByUserId(@Param("userId") String userId);

    @Query("SELECT u FROM UsersEntity u WHERE u.nickName = :nickName")
    UsersEntity findByNickName(String nickName);

    @Query("SELECT u FROM UsersEntity u WHERE u.userId = :userId")
    UsersEntity findByEmailId(String userId);

    UsersEntity findByUserIdAndSignUpType(String userId, SignUpType signUpType);
}
