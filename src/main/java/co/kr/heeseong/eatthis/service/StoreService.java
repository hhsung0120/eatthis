package co.kr.heeseong.eatthis.service;

import co.kr.heeseong.eatthis.Enum.ErrorCodeType;
import co.kr.heeseong.eatthis.Enum.TableCodeType;
import co.kr.heeseong.eatthis.entity.StoreEntity;
import co.kr.heeseong.eatthis.mapper.ReviewMapper;
import co.kr.heeseong.eatthis.model.CommonFile;
import co.kr.heeseong.eatthis.model.Review;
import co.kr.heeseong.eatthis.model.Store;
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

import static java.util.stream.Collectors.toList;



@Service
@Transactional
@RequiredArgsConstructor
public class StoreService {

    private final UserService userService;

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
    public List<Store> getMainList(int locationX, int locationY) {
        Page<StoreEntity> storeEntityList = storeRepository.findAll(PageRequest.of(1,10, Sort.Direction.DESC,"createDate"));
        storeEntityList.stream().forEach(
                list -> System.out.println(list.toString())
        );
        return storeEntityList.stream()
                                .map(list -> new Store(list.getStoreIdx(), list.getStoreId(), list.getCategory()
                                                        , list.getStoreName(), list.getLocationX(), list.getLocationY()
                                                        , list.getCreateDate(), list.getLastModifiedDate()))
                                .collect(toList());
    }

    /**
     * 리뷰 등록 & 수정
     * @param review
     */
    public long saveReview(Review review) {
        return review.getIdx() == 0 ? this.insertReview(review) : this.updateReview(review);
    }

    private long insertReview(Review review) {
        userDetailRepository.findById(userService.getAccountUserIdx()).orElseThrow(() -> new RuntimeException(ErrorCodeType.USER_NOT_FOUND.getValue() + " -> " + review.getIdx()));
        storeRepository.findById(review.getStoreIdx()).orElseThrow(() -> new RuntimeException(ErrorCodeType.STORE_NOT_FOUND.getValue() + " -> " + review.getStoreIdx()));

        System.out.println(review.toString());
        //없는 메뉴도 검사할것
        if(review.getTotalPrice() > 100000){
            throw new IllegalArgumentException("유효한 금액을 입력 하세요.");
        }

        review.setUserIdx(userService.getAccountUserIdx());
        Long idx = reviewRepository.save(review.toEntity()).getIdx();
        if(idx > 0){
            review.getFile().stream().forEach(file -> {
                try{
                    if(!file.isEmpty()){
                        CommonFile commonFile = FileUtil.executeFileUpload(file, uploadPath);
                        commonFile.setTableIdx(idx);
                        commonFile.setTableType(TableCodeType.REVIEW);
                        fileRepository.save(commonFile.toEntity());
                    }
                }catch (Exception e){
                    throw new RuntimeException(ErrorCodeType.FILE_UPLOAD_ERROR.getValue());
                }
            });
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

