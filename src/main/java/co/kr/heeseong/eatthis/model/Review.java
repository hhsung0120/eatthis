package co.kr.heeseong.eatthis.model;

import co.kr.heeseong.eatthis.service.entity.ReviewEntity;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;


@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Review {

    private long idx;
    private long storeIdx;
    private long userIdx;
    private long menuIdx;
    private String contents;
    private int totalPrice;
    private List<MultipartFile> file;
    private float star;
    private LocalDateTime createDate;
    private LocalDateTime lastModifiedDate;
    private String userName;
    private String sex;
    private String storeName;
    private String menuName;

    public ReviewEntity toEntity(){
        return ReviewEntity.builder()
                .idx(idx)
                .storeIdx(storeIdx)
                .userIdx(userIdx)
                .menuIdx(menuIdx)
                .contents(contents)
                .totalPrice(totalPrice)
                .star(star)
                .build();
    }
}
