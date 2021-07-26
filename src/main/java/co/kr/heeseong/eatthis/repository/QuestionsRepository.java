package co.kr.heeseong.eatthis.repository;

import co.kr.heeseong.eatthis.entity.QuestionsEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QuestionsRepository extends JpaRepository<QuestionsEntity,Long> {

    @Query("SELECT COUNT(q) FROM QuestionsEntity q WHERE q.userIdx = :userIdx")
    int findAllCount(@Param("userIdx") long userIdx);

    @Query("SELECT q FROM QuestionsEntity q WHERE q.userIdx = :userIdx ORDER BY q.idx DESC")
    List<QuestionsEntity> findByUserIdx(@Param("userIdx") long userIdx);
}
