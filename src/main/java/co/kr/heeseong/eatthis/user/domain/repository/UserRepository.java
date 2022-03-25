package co.kr.heeseong.eatthis.user.domain.repository;

import co.kr.heeseong.eatthis.user.domain.entity.UsersEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<UsersEntity, Long> {

    @Query("SELECT u FROM UsersEntity u WHERE u.seq = :seq AND u.password = :password")
    UsersEntity findByIdAndPassword(@Param("seq") String seq, @Param("password") String password);

    @Query("SELECT u FROM UsersEntity u WHERE u.seq = :seq")
    UsersEntity findByEmailId(@Param("seq") String seq);
}
