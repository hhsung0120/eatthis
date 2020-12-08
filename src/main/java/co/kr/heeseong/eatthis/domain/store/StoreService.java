package co.kr.heeseong.eatthis.domain.store;

import co.kr.heeseong.eatthis.Enum.ErrorCode;
import co.kr.heeseong.eatthis.Enum.TableCode;
import co.kr.heeseong.eatthis.domain.common.FileRepository;
import co.kr.heeseong.eatthis.domain.store.review.ReviewRepository;
import co.kr.heeseong.eatthis.domain.user.UserDetailEntity;
import co.kr.heeseong.eatthis.domain.user.UserDetailRepository;
import co.kr.heeseong.eatthis.dto.CommonFile;
import co.kr.heeseong.eatthis.dto.Review;
import co.kr.heeseong.eatthis.dto.Store;
import co.kr.heeseong.eatthis.util.FileUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class StoreService {

    private final StoreRepository storeRepository;
    private final ReviewRepository reviewRepository;
    private final FileRepository fileRepository;
    private final UserDetailRepository userDetailRepository;

    @Value("${default-upload-path}")
    private String uploadPath;

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
     * 리뷰 등록 & 수정
     * @param review
     */
    @Transactional
    public void saveReview(Review review){
        if(review.getIdx() < 1){
            this.insertReview(review);
        }else{
            this.updateReview(review);
        }
    }

    private void insertReview(Review review) {
        userDetailRepository.findById(review.getUserIdx()).orElseThrow(() -> new IllegalArgumentException(ErrorCode.USER_NOT_FOUNT.getValue() + " -> " + review.getIdx()));
        storeRepository.findById(review.getStoreIdx()).orElseThrow(() -> new IllegalArgumentException(ErrorCode.STORE_NOT_FOUNT.getValue() + " -> " + review.getStoreIdx()));
        //없는 메뉴도 검사할것

        long idx = reviewRepository.save(review.toEntity()).getIdx();
        if(idx > 0){
            CommonFile commonFile;
            for(MultipartFile file : review.getFile()){
                if(!file.isEmpty()){
                    commonFile = FileUtil.executeFileUpload(file, uploadPath);
                    commonFile.setTableIdx(idx);
                    commonFile.setTableType(TableCode.REVIEW);
                    fileRepository.save(commonFile.toEntity());
                }
            }
        }
    }

    private void updateReview(Review review) {

    }
}
