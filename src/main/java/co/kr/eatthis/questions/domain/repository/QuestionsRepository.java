package co.kr.eatthis.questions.domain.repository;

import co.kr.eatthis.questions.domain.entity.QuestionsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionsRepository extends JpaRepository<QuestionsEntity, Long> {

//    @Query("SELECT COUNT(q) FROM QuestionsEntity q WHERE q.userIdx = :userIdx")
//    int findAllCount(@Param("userIdx") long userIdx);
//
//    @Query("SELECT q FROM QuestionsEntity q WHERE q.userIdx = :userIdx ORDER BY q.idx DESC")
//    List<QuestionsEntity> findByUserIdx(@Param("userIdx") long userIdx);
}
