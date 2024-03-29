package co.kr.eatthis.questions.domain.repository;

import co.kr.eatthis.questions.domain.entity.QuestionsEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QuestionsRepository extends JpaRepository<QuestionsEntity, Long> {

    @Query("SELECT COUNT(q) FROM QuestionsEntity q WHERE q.userSeq = :userSeq AND q.categorySeq = :searchSeq")
    int findAllCount(@Param("userSeq") long userSeq, @Param("searchSeq") long searchSeq);

//
//    @Query("SELECT q FROM QuestionsEntity q WHERE q.userIdx = :userIdx ORDER BY q.idx DESC")
//    List<QuestionsEntity> findByUserIdx(@Param("userIdx") long userIdx);
}
