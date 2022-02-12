package co.kr.heeseong.eatthis.user.domain.repository;

import co.kr.heeseong.eatthis.user.domain.entity.UserSecessionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserSecessionRepository extends JpaRepository<UserSecessionEntity, Long> {

    UserSecessionEntity findByUserIdx(Long userIdx);
}
