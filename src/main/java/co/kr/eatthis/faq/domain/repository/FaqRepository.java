package co.kr.eatthis.faq.domain.repository;

import co.kr.eatthis.faq.domain.entity.FaqEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FaqRepository extends JpaRepository<FaqEntity, Long> {

}
