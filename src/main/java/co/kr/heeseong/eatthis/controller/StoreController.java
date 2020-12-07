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

    @PostMapping("/{storeIdx}/saveReview")
    public Map<String, Object> saveReview(@PathVariable long storeIdx, Review review){
        Map<String, Object> result = new LinkedHashMap<>();
        result.put("storeIdx",storeIdx);
        return result;
    }

}
