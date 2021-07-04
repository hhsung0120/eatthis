package co.kr.heeseong.eatthis.repository;

import co.kr.heeseong.eatthis.entity.common.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<FileEntity,Long> {

}
