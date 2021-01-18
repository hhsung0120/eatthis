package co.kr.heeseong.eatthis.service.repository;

import co.kr.heeseong.eatthis.service.entity.ReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReviewRepository extends JpaRepository<ReviewEntity,Long> {

    @Query("SELECT r FROM ReviewEntity r WHERE r.userIdx = :userIdx")
    List<ReviewEntity> findAllByUserIdx(long userIdx);
}
