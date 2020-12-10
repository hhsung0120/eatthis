package co.kr.heeseong.eatthis.service.repository;

import co.kr.heeseong.eatthis.service.entity.TestEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestRepository extends JpaRepository<TestEntity,Long> {

}
