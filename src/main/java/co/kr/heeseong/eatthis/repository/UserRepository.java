package co.kr.heeseong.eatthis.repository;

import co.kr.heeseong.eatthis.entity.UserEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity,Long> {

    @Query("SELECT u FROM UserEntity u WHERE u.id = :id AND u.password = :password")
    Optional<UserEntity> findByIdAndPassword(@Param("id") String id, @Param("password") String password);

    @Query("SELECT u FROM UserEntity u WHERE u.id = :id")
    Optional<UserEntity> findByEmailId(@Param("id") String id);
}
