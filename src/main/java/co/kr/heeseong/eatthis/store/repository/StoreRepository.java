package co.kr.heeseong.eatthis.store.repository;

import co.kr.heeseong.eatthis.store.domain.entity.StoreEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<StoreEntity,Long> {

}
