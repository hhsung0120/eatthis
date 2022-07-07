package co.kr.eatthis.common.domain.repository;

import co.kr.eatthis.common.domain.entity.TestEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestRepository extends JpaRepository<TestEntity, Long> {

}
