package co.kr.heeseong.eatthis.common.domain.repository;

import co.kr.heeseong.eatthis.common.domain.entity.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<FileEntity, Long> {

}
