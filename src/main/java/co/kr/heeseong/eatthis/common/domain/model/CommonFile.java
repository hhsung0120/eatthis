package co.kr.heeseong.eatthis.common.domain.model;

import co.kr.heeseong.eatthis.common.Enum.TableCodeType;
import co.kr.heeseong.eatthis.common.domain.entity.FileEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CommonFile {
    private long idx;
    private TableCodeType tableType;
    private long tableIdx;
    private String uuid;
    private String originalFileName;
    private String extension;
    private String uploadPath;

    public FileEntity toEntity() {
        return FileEntity.builder()
                .idx(idx)
                .tableType(tableType)
                .tableIdx(tableIdx)
                .uuid(uuid)
                .originalFileName(originalFileName)
                .extension(extension)
                .build();
    }
}
