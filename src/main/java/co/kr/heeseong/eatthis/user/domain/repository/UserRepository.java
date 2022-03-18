package co.kr.heeseong.eatthis.user.domain.repository;

import co.kr.heeseong.eatthis.user.domain.entity.UsersEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<UsersEntity, Long> {

    @Query("SELECT u FROM UsersEntity u WHERE u.id = :id AND u.password = :password")
    UsersEntity findByIdAndPassword(@Param("id") String id, @Param("password") String password);

    @Query("SELECT u FROM UsersEntity u WHERE u.id = :id")
    UsersEntity findByEmailId(@Param("id") String id);
}
