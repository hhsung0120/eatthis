package co.kr.heeseong.eatthis.common.repository;

import co.kr.heeseong.eatthis.common.entity.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<FileEntity,Long> {

}
