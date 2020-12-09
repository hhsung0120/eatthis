package co.kr.heeseong.eatthis.controller;

import co.kr.heeseong.eatthis.domain.store.StoreService;
import co.kr.heeseong.eatthis.dto.Review;
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

    @GetMapping("/{userIdx}/{storeIdx}/{menuIdx}/{reviewIdx}/saveReview")
    public Map<String, Object> saveReview(){
        Map<String, Object> result = new LinkedHashMap<>();
        result.put("[request - post]", "");
        result.put("contents", "리뷰내용입니다.");
        result.put("file", "멀티플로 보내고 받을 수 있습니다.");
        result.put("totalPrice", "총 금액");
        result.put("star", "별점");
        result.put("[response]", "");
        result.put("[성공]", "reviewIdx : 0 보다 큼");
        result.put("[실패]", "reviewIdx : 0, reason : e.getMessage()");
        result.put("", "");
        result.put(" ", "");
        result.put("  ", "");
        result.put("comment", "if userIdx > 0 성공으로 보면되고 else reason 출력하시면 됩니다. ");
        result.put("comment", "리뷰 IDX 는 최초 등록 시 0 으로 보내면 됩니다.");
        result.put("TODO", "메뉴 구조가 현재 없어서 없는 메뉴에대한 예외를 처리해야함");
        result.put("TODO", "리뷰 수정 기능이 있었나 ? 기획에..? 현재는 없음 구현 해야함");

        return result;
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
