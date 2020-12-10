package co.kr.heeseong.eatthis.service.repository;

import co.kr.heeseong.eatthis.service.entity.common.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<FileEntity,Long> {

}
