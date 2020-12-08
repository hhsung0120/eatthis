package co.kr.heeseong.eatthis.dto;

import co.kr.heeseong.eatthis.domain.store.review.ReviewEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;


@Getter
@Setter
@ToString
@NoArgsConstructor
public class Review {

    private long idx;
    private long storeIdx;
    private long userIdx;
    private long menuIdx;
    private String contents;
    private int totalPrice;
    private List<MultipartFile> file;
    private LocalDateTime createDate;
    private LocalDateTime lastModifiedDate;

    public ReviewEntity toEntity(){
        return ReviewEntity.builder()
                .idx(idx)
                .storeIdx(storeIdx)
                .menuIdx(menuIdx)
                .userIdx(userIdx)
                .contents(contents)
                .totalPrice(totalPrice)
                .build();
    }
}
