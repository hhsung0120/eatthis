package co.kr.eatthis.questions.domain.model;

import co.kr.eatthis.store.domain.entity.ReviewEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;


@Getter
@Setter
@ToString
public class Review {

    private Long idx = 0L;
    private Long storeIdx;
    private Long userIdx;
    private Long menuIdx;
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

    public Review() {
    }

    @Builder
    public Review(Long idx, Long storeIdx, Long userIdx, Long menuIdx, String contents, int totalPrice, List<MultipartFile> file, float star, LocalDateTime createDate, LocalDateTime lastModifiedDate, String userName, String sex, String storeName, String menuName) {
        this.idx = idx;
        this.storeIdx = storeIdx;
        this.userIdx = userIdx;
        this.menuIdx = menuIdx;
        this.contents = contents;
        this.totalPrice = totalPrice;
        this.file = file;
        this.star = star;
        this.createDate = createDate;
        this.lastModifiedDate = lastModifiedDate;
        this.userName = userName;
        this.sex = sex;
        this.storeName = storeName;
        this.menuName = menuName;
    }

    public ReviewEntity toEntity() {
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
