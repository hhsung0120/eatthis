package co.kr.heeseong.eatthis.service.repository;

import co.kr.heeseong.eatthis.service.entity.FaqCategoryEntity;
import org.apache.ibatis.annotations.Select;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FaqCategoryRepository extends JpaRepository<FaqCategoryEntity,Long> {

    @Query("SELECT c FROM FaqCategoryEntity c WHERE c.idx = :categoryIdx")
    FaqCategoryEntity findByCategoryIdx(long categoryIdx);
}
