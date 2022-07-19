package co.kr.eatthis.user.domain.repository;

import co.kr.eatthis.user.domain.entity.SecessionReasonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SecessionReasonRepository extends JpaRepository<SecessionReasonEntity, Long> {

    @Query("SELECT s FROM SecessionReasonEntity s WHERE s.useYn = 'Y' ORDER BY s.orderNumber ASC")
    List<SecessionReasonEntity> findAllForOrderNumberAsc();
}
