package co.kr.eatthis.store.domain.model;

import co.kr.eatthis.store.domain.entity.StoreEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Getter
@ToString
public class Store {
    private long storeIdx;
    private long storeId;
    private String category;
    private String storeName;
    private String locationX;
    private String locationY;
    private String createDate;
    private String lastModifiedDate;

    public Store() {
    }

    public StoreEntity toEntity() {
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
    public Store(long storeIdx, long storeId, String category, String storeName, String locationX, String locationY, LocalDateTime createDate, LocalDateTime lastModifiedDate) {
        this.storeIdx = storeIdx;
        this.storeId = storeId;
        this.category = category;
        this.storeName = storeName;
        this.locationX = locationX;
        this.locationY = locationY;
        this.createDate = createDate == null ? "" : createDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        this.lastModifiedDate = lastModifiedDate == null ? "" : lastModifiedDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    public List<Store> entityToVoList(Page<StoreEntity> storeEntityList) {
//        return storeEntityList.stream()
//                .map(list -> new Store(list.getStoreIdx(), list.getStoreId(), list.getCategory()
//                        , list.getStoreName(), list.getLocationX(), list.getLocationY()
//                        , list.getCreateDate(), list.getLastModifiedDate()))
//                .collect(toList());
        return new ArrayList<>();
    }
}
