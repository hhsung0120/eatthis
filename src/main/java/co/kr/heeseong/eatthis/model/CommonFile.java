package co.kr.heeseong.eatthis.model;

import co.kr.heeseong.eatthis.Enum.TableCode;
import co.kr.heeseong.eatthis.service.entity.common.FileEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CommonFile {
    private long idx;
    private TableCode tableType;
    private long tableIdx;
    private String uuid;
    private String originalFileName;
    private String extension;
    private String uploadPath;

    public FileEntity toEntity(){
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
