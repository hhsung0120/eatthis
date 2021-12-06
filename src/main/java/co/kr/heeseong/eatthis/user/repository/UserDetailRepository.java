package co.kr.heeseong.eatthis.user.repository;

import co.kr.heeseong.eatthis.user.domain.entity.UserDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDetailRepository extends JpaRepository<UserDetailEntity,Long> {

}
