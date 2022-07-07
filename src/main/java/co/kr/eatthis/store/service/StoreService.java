package co.kr.eatthis.store.service;

import co.kr.eatthis.common.Enum.ErrorCode;
import co.kr.eatthis.common.Enum.TableCodeType;
import co.kr.eatthis.common.domain.model.CommonFile;
import co.kr.eatthis.common.domain.repository.FileRepository;
import co.kr.eatthis.common.util.FileUtil;
import co.kr.eatthis.mapper.ReviewMapper;
import co.kr.eatthis.questions.domain.model.Review;
import co.kr.eatthis.store.domain.entity.StoreEntity;
import co.kr.eatthis.store.domain.model.Store;
import co.kr.eatthis.store.domain.repository.ReviewRepository;
import co.kr.eatthis.store.domain.repository.StoreRepository;
import co.kr.eatthis.user.domain.repository.UserDetailRepository;
import co.kr.eatthis.user.service.UserService;
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
        userDetailRepository.findById(0L).orElseThrow(() -> new RuntimeException(ErrorCode.USER_NOT_FOUND.getMessageEn() + " -> " + review.getIdx()));
        storeRepository.findById(review.getStoreIdx()).orElseThrow(() -> new RuntimeException(ErrorCode.STORE_NOT_FOUND.getMessageEn() + " -> " + review.getStoreIdx()));

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
                    throw new RuntimeException(ErrorCode.FILE_UPLOAD_ERROR.getMessageEn());
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
        userDetailRepository.findById(userIdx).orElseThrow(() -> new IllegalArgumentException(ErrorCode.USER_NOT_FOUND.getMessageEn() + " -> " + userIdx));
        return reviewMapper.selectReviewList(userIdx);
    }
}

