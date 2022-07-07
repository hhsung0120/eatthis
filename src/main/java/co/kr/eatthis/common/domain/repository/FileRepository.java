package co.kr.eatthis.common.domain.repository;

import co.kr.eatthis.common.domain.entity.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<FileEntity, Long> {

}
