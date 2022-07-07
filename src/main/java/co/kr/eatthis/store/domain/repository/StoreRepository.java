package co.kr.eatthis.store.domain.repository;

import co.kr.eatthis.store.domain.entity.StoreEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<StoreEntity, Long> {

}
