package co.kr.heeseong.eatthis.service;

import co.kr.heeseong.eatthis.Enum.ErrorCodeType;
import co.kr.heeseong.eatthis.Enum.TableCodeType;
import co.kr.heeseong.eatthis.mapper.ReviewMapper;
import co.kr.heeseong.eatthis.model.CommonFile;
import co.kr.heeseong.eatthis.model.Review;
import co.kr.heeseong.eatthis.model.Store;
import co.kr.heeseong.eatthis.entity.StoreEntity;
import co.kr.heeseong.eatthis.repository.FileRepository;
import co.kr.heeseong.eatthis.repository.ReviewRepository;
import co.kr.heeseong.eatthis.repository.StoreRepository;
import co.kr.heeseong.eatthis.repository.UserDetailRepository;
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
    private final ReviewMapper reviewMapper;

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
    public long saveReview(Review review) {
        if(review.getIdx() == 0){
            return this.insertReview(review);
        }else{
            return this.updateReview(review);
        }
    }

    private long insertReview(Review review){
        userDetailRepository.findById(review.getUserIdx()).orElseThrow(() -> new IllegalArgumentException(ErrorCodeType.USER_NOT_FOUND.getValue() + " -> " + review.getIdx()));
        storeRepository.findById(review.getStoreIdx()).orElseThrow(() -> new IllegalArgumentException(ErrorCodeType.STORE_NOT_FOUND.getValue() + " -> " + review.getStoreIdx()));
        //없는 메뉴도 검사할것
        if(review.getTotalPrice() > 100000){
            throw new IllegalArgumentException("유효한 금액을 입력 하세요.");
        }

        long idx = reviewRepository.save(review.toEntity()).getIdx();
        if(idx > 0){
            CommonFile commonFile;
            for(MultipartFile file : review.getFile()){
                if(!file.isEmpty()){
                    commonFile = FileUtil.executeFileUpload(file, uploadPath);
                    commonFile.setTableIdx(idx);
                    commonFile.setTableType(TableCodeType.REVIEW);
                    fileRepository.save(commonFile.toEntity());
                }
            }
        }
        return idx;
    }

    private long updateReview(Review review) {
        return 0;
    }

    /**
     * 리뷰 리스트
     * @param userIdx
     * @return
     */
    public List<Review> getReviewList(long userIdx) {
        userDetailRepository.findById(userIdx).orElseThrow(() -> new IllegalArgumentException(ErrorCodeType.USER_NOT_FOUND.getValue() + " -> " + userIdx));
        return reviewMapper.selectReviewList(userIdx);
    }
}
