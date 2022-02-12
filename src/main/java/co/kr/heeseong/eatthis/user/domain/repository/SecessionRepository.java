package co.kr.heeseong.eatthis.user.domain.repository;

import co.kr.heeseong.eatthis.user.domain.entity.SecessionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SecessionRepository extends JpaRepository<SecessionEntity, Long> {

    @Query("SELECT s FROM SecessionEntity s WHERE s.status = 'Y' ORDER BY s.order ASC")
    List<SecessionEntity> findAllForOrderNumberAsc();
}
