package co.kr.heeseong.eatthis.domain.store;

import co.kr.heeseong.eatthis.domain.store.review.ReviewRepository;
import co.kr.heeseong.eatthis.dto.CommonFile;
import co.kr.heeseong.eatthis.dto.Review;
import co.kr.heeseong.eatthis.dto.Store;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@Service
public class StoreService {

    private StoreRepository storeRepository;
    private ReviewRepository reviewRepository;

    /**
     * 메인 리스트
     * @return Map<String, Object>
     */
    public Map<String, Object> getMainList(int locationX, int locationY) {
        Map<String, Object> result = new HashMap<>();

        Page<StoreEntity> storeEntityList = storeRepository.findAll(PageRequest.of(1,10, Sort.Direction.DESC,"createDate"));
        List<Store> storeDtoList = new ArrayList<>();
        for(StoreEntity storeEntity : storeEntityList){
            Store storeDto = Store.builder()
                    .storeIdx(storeEntity.getStoreIdx())
                    .category(storeEntity.getCategory())
                    .storeId(storeEntity.getStoreId())
                    .storeName(storeEntity.getStoreName())
                    .locationX(storeEntity.getLocationX())
                    .locationY(storeEntity.getLocationY())
                    .createDate(storeEntity.getCreateDate())
                    .lastModifiedDate(storeEntity.getLastModifiedDate())
                    .build();

            storeDtoList.add(storeDto);
        }
        result.put("storeList", storeDtoList);

        return result;
    }

    /**
     * 리뷰 등록
     * @param review
     */
    public void saveReview(Review review) {
        long idx;
        if(review.getIdx() < 1){
            idx = reviewRepository.save(review.toEntity()).getIdx();
            if(idx > 0){
                CommonFile commonFile;
                for(MultipartFile m : review.getFile()){
                    if(!m.isEmpty()){

                    }
                }
            }
        }
    }
}
