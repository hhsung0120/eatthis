package co.kr.heeseong.eatthis.dto;

import co.kr.heeseong.eatthis.domain.notice.NoticeEntity;
import co.kr.heeseong.eatthis.domain.store.StoreEntity;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class StoreDto {
    private Long storeIdx;
    private Long storeId;
    private String category;
    private String storeName;
    private String locationX;
    private String locationY;
    private LocalDateTime createDate;
    private LocalDateTime lastModifiedDate;

    public StoreEntity toEntity(){
        return StoreEntity.builder()
                .storeIdx(storeIdx)
                .storeId(storeId)
                .category(category)
                .storeName(storeName)
                .storeName(locationX)
                .storeName(locationY)
                .build();
    }

    @Builder
    public StoreDto(Long storeIdx, Long storeId, String category, String storeName, String locationX,String locationY,LocalDateTime createDate, LocalDateTime lastModifiedDate){
        this.storeIdx = storeIdx;
        this.storeId = storeId;
        this.category = category;
        this.storeName = storeName;
        this.locationX = locationX;
        this.locationY = locationY;
        this.createDate = createDate;
        this.lastModifiedDate = lastModifiedDate;
    }

}
