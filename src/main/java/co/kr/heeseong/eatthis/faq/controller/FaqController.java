package co.kr.heeseong.eatthis.faq.controller;

import co.kr.heeseong.eatthis.common.Enum.StatusCode;
import co.kr.heeseong.eatthis.common.domain.model.ResponseData;
import co.kr.heeseong.eatthis.faq.service.FaqService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;


/**
 * 자주묻는 질문
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/faqs")
public class FaqController {

    final FaqService faqService;

    @GetMapping("/{page}")
    public ResponseEntity<ResponseData> faqList(@PathVariable int page) {
        try {
            Map<String, Object> data = new HashMap<>();
            data.put("list", faqService.getFaqList(page));

            ResponseData responseData = new ResponseData(
                    StatusCode.OK.getValue()
                    , StatusCode.OK.toString()
                    , data);
            return ResponseEntity.ok(responseData);
        } catch (Exception e) {
            return ResponseEntity.ok(new ResponseData(e.getMessage()));
        }
    }

}
