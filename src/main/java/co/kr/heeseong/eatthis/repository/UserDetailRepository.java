package co.kr.heeseong.eatthis.repository;

import co.kr.heeseong.eatthis.entity.UserDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDetailRepository extends JpaRepository<UserDetailEntity,Long> {

}
