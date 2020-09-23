package co.kr.heeseong.eatthis.domain.notice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NoticeRepository extends JpaRepository<NoticeEntity,Long> {

    @Query("SELECT n FROM NoticeEntity n ORDER BY n.regDate DESC")
    List<NoticeEntity> findAllDesc();
}