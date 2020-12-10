package co.kr.heeseong.eatthis.service.repository;

import co.kr.heeseong.eatthis.service.entity.UserDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDetailRepository extends JpaRepository<UserDetailEntity,Long> {

}
