package co.kr.heeseong.eatthis.controller;

import co.kr.heeseong.eatthis.service.StoreService;
import co.kr.heeseong.eatthis.model.Review;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 메인
 */
@Log4j2
@RestController
@RequestMapping("/store")
@RequiredArgsConstructor
public class StoreController {

    private final StoreService storeService;

    @GetMapping("/list/{locationX}/{locationY}")
    public Map<String, Object> mainList(@PathVariable int locationX, @PathVariable int locationY){
        return storeService.getMainList(locationX, locationY);
    }

    @PostMapping("/{userIdx}/{storeIdx}/{menuIdx}/{reviewIdx}/saveReview")
    public Map<String, Object> saveReview(@PathVariable long userIdx
                                        , @PathVariable long storeIdx
                                        , @PathVariable long menuIdx
                                        , @PathVariable long reviewIdx
                                        , @ModelAttribute Review review){
        Map<String, Object> result = new LinkedHashMap<>();
        result.put("reviewIdx", 0);

        try {
            review.setUserIdx(userIdx);
            review.setStoreIdx(storeIdx);
            review.setMenuIdx(menuIdx);
            review.setIdx(reviewIdx);
            result.put("reviewIdx", storeService.saveReview(review));
        }catch (IllegalArgumentException e){
            result.put("reason", e.getMessage());
        }catch (Exception e){
            result.put("reason", "리뷰 저장 실패 또는 파일 업로드, 저장 실패 입니다.");
        }

        return result;
    }

}
