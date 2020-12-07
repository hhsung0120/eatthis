package co.kr.heeseong.eatthis.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;


@Getter
@ToString
@NoArgsConstructor
public class Review {

    private long idx;
    private long storeIdx;
    private String contents;
    private MultipartFile file;
    private LocalDateTime createDate;
    private LocalDateTime lastModifiedDate;
}
