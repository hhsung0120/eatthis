package co.kr.heeseong.eatthis.model;

import co.kr.heeseong.eatthis.Enum.TableCodeType;
import co.kr.heeseong.eatthis.entity.common.FileEntity;
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
