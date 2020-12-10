package co.kr.heeseong.eatthis.service.repository;

import co.kr.heeseong.eatthis.service.entity.NoticeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NoticeRepository extends JpaRepository<NoticeEntity,Long> {

    @Query("SELECT n FROM NoticeEntity n ORDER BY n.createDate DESC")
    List<NoticeEntity> findAllDesc();
}
