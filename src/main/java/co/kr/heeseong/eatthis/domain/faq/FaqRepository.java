package co.kr.heeseong.eatthis.domain.faq;

import co.kr.heeseong.eatthis.domain.notice.NoticeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FaqRepository extends JpaRepository<FaqEntity,Long> {

    @Query("SELECT f FROM FaqEntity f ORDER BY f.idx DESC")
    List<NoticeEntity> findAllDesc();
}
