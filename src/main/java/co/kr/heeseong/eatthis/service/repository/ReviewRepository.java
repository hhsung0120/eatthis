package co.kr.heeseong.eatthis.service.repository;

import co.kr.heeseong.eatthis.service.entity.ReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<ReviewEntity,Long> {

}
