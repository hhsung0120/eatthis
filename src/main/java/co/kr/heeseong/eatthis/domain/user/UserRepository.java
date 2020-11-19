package co.kr.heeseong.eatthis.domain.user;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<UserEntity,Long> {

    @Query("SELECT COUNT(u) FROM UserEntity u WHERE u.id = :id AND u.password = :password")
    int findByIdAndPassword(@Param("id") String id, @Param("password") String password);

    @Query("SELECT u FROM UserEntity u WHERE u.id = :id")
    UserEntity findByEmailId(@Param("id") String id);
}
