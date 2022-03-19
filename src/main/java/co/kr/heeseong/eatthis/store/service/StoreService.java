package co.kr.heeseong.eatthis.store.service;

import co.kr.heeseong.eatthis.common.Enum.ErrorCodeType;
import co.kr.heeseong.eatthis.common.Enum.TableCodeType;
import co.kr.heeseong.eatthis.common.domain.model.CommonFile;
import co.kr.heeseong.eatthis.common.domain.repository.FileRepository;
import co.kr.heeseong.eatthis.common.util.FileUtil;
import co.kr.heeseong.eatthis.mapper.ReviewMapper;
import co.kr.heeseong.eatthis.questions.domain.model.Review;
import co.kr.heeseong.eatthis.store.domain.entity.StoreEntity;
import co.kr.heeseong.eatthis.store.domain.model.Store;
import co.kr.heeseong.eatthis.store.domain.repository.ReviewRepository;
import co.kr.heeseong.eatthis.store.domain.repository.StoreRepository;
import co.kr.heeseong.eatthis.user.domain.repository.UserDetailRepository;
import co.kr.heeseong.eatthis.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
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
     *
     * @return Map<String, Object>
     */
    @Transactional
    public List<Store> getMainList(int locationX, int locationY) {
        Page<StoreEntity> storeEntityList = storeRepository.findAll(PageRequest.of(1, 10, Sort.Direction.DESC, "createDate"));
        return new Store().entityToVoList(storeEntityList);
    }

    /**
     * 리뷰 등록 & 수정
     *
     * @param review
     */
    @Transactional
    public long saveReview(Review review) {
        return review.getIdx() == 0 ? this.insertReview(review) : this.updateReview(review);
    }

    private long insertReview(Review review) {
        userDetailRepository.findById(0L).orElseThrow(() -> new RuntimeException(ErrorCodeType.USER_NOT_FOUND.getValue() + " -> " + review.getIdx()));
        storeRepository.findById(review.getStoreIdx()).orElseThrow(() -> new RuntimeException(ErrorCodeType.STORE_NOT_FOUND.getValue() + " -> " + review.getStoreIdx()));

        System.out.println(review.toString());
        //없는 메뉴도 검사할것
        if (review.getTotalPrice() > 100000) {
            throw new IllegalArgumentException("유효한 금액을 입력 하세요.");
        }

        review.setUserIdx(0L);
        Long idx = reviewRepository.save(review.toEntity()).getIdx();
        if (idx > 0) {
            review.getFile().stream().forEach(file -> {
                try {
                    if (!file.isEmpty()) {
                        CommonFile commonFile = FileUtil.executeFileUpload(file, uploadPath);
                        commonFile.setTableIdx(idx);
                        commonFile.setTableType(TableCodeType.REVIEW);
                        fileRepository.save(commonFile.toEntity());
                    }
                } catch (Exception e) {
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
     *
     * @param userIdx
     * @return
     */
    public List<Review> getReviewList(long userIdx) {
        userDetailRepository.findById(userIdx).orElseThrow(() -> new IllegalArgumentException(ErrorCodeType.USER_NOT_FOUND.getValue() + " -> " + userIdx));
        return reviewMapper.selectReviewList(userIdx);
    }
}

