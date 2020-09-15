package co.kr.heeseong.eatthis.domain.test;

import co.kr.heeseong.eatthis.domain.test.TestEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestRepository extends JpaRepository<TestEntity,Long> {

}
