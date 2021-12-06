package co.kr.heeseong.eatthis.faq.repository;

import co.kr.heeseong.eatthis.faq.domain.entity.FaqEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FaqRepository extends JpaRepository<FaqEntity,Long> {

}
