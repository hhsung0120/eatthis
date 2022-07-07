package co.kr.eatthis.notice.domain.repository;

import co.kr.eatthis.notice.domain.entity.NoticeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeRepository extends JpaRepository<NoticeEntity, Long> {

//    @Query("SELECT n FROM NoticeEntity n ORDER BY n.createDate DESC")
//    List<NoticeEntity> findAllDesc();
}
