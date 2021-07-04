package co.kr.heeseong.eatthis.repository;

import co.kr.heeseong.eatthis.entity.TestEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestRepository extends JpaRepository<TestEntity,Long> {

}
