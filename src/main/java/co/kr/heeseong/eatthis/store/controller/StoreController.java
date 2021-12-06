package co.kr.heeseong.eatthis.store.controller;

import co.kr.heeseong.eatthis.common.Enum.StatusCode;
import co.kr.heeseong.eatthis.common.model.ResponseData;
import co.kr.heeseong.eatthis.questions.domain.model.Review;
import co.kr.heeseong.eatthis.store.service.StoreService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 메인
 */
@Log4j2
@RestController
@RequestMapping("/stores")
@RequiredArgsConstructor
public class StoreController {

    private final StoreService storeService;

    @GetMapping("/{locationX}/{locationY}")
    public ResponseEntity<ResponseData> mainList(@PathVariable int locationX, @PathVariable int locationY){
        try{
            Map<String, Object> data = new HashMap<>();
            data.put("list", storeService.getMainList(locationX, locationY));

            ResponseData responseData = new ResponseData(
                    StatusCode.OK.getValue()
                    , StatusCode.OK.toString()
                    , data);
            return ResponseEntity.ok(responseData);
        }catch (Exception e){
            return ResponseEntity.ok(new ResponseData(e.getMessage()));
        }
    }

    @PostMapping("/reviews/{storeIdx}/{menuIdx}")
    public ResponseEntity<ResponseData> reviews(@ModelAttribute Review review){
        try{
            storeService.saveReview(review);

            ResponseData responseData = new ResponseData(
                    StatusCode.OK.getValue()
                    , StatusCode.OK.toString());
            return ResponseEntity.ok(responseData);
        }catch (Exception e){
            return ResponseEntity.ok(new ResponseData(e.getMessage()));
        }
    }

    @GetMapping("/reviews/{userIdx}")
    public Map<String, Object> reviews(@PathVariable long userIdx){
        Map<String, Object> result = new LinkedHashMap<>();

        try{
            result.put("dataList", storeService.getReviewList(userIdx));
        }catch (Exception e){
            result.put("reason", "관리자에게 문의 하세요.");
        }

        return result;
    }

}
