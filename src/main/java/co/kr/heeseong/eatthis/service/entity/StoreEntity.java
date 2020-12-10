package co.kr.heeseong.eatthis.service.entity;

import co.kr.heeseong.eatthis.service.entity.common.TimeEntity;
import lombok.*;

import javax.persistence.*;

@ToString
@Entity
@Getter
@Table(name = "store")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StoreEntity extends TimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long storeIdx;

    @Column
    private String category;

    @Column
    private Long storeId;

    @Column
    private String storeName;

    @Column(name="location_x")
    private String locationX;

    @Column(name="location_y")
    private String locationY;

    @Builder
    public StoreEntity(Long storeIdx, String category, Long storeId, String storeName, String locationX, String locationY){
        this.storeIdx = storeIdx;
        this.category = category;
        this.storeId = storeId;
        this.storeName = storeName;
        this.locationX = locationX;
        this.locationY = locationY;
    }


}
