package co.kr.heeseong.eatthis.faq.repository;

import co.kr.heeseong.eatthis.faq.domain.entity.FaqCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FaqCategoryRepository extends JpaRepository<FaqCategoryEntity,Long> {

    @Query("SELECT c FROM FaqCategoryEntity c WHERE c.idx = :categoryIdx")
    FaqCategoryEntity findByCategoryIdx(long categoryIdx);
}
