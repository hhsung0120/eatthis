package co.kr.heeseong.eatthis.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class CommonFile {
    private long idx;
    private String uploadPath;
    private String uuid;
    private String originalFileName;
    private String extension;
}
