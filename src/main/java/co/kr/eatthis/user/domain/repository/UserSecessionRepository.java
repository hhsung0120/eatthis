package co.kr.eatthis.user.domain.repository;

import co.kr.eatthis.user.domain.entity.UserSecessionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserSecessionRepository extends JpaRepository<UserSecessionEntity, Long> {
}
