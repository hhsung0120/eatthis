package co.kr.eatthis.user.domain.repository;

import co.kr.eatthis.user.domain.entity.UserDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDetailRepository extends JpaRepository<UserDetailEntity, Long> {

}
