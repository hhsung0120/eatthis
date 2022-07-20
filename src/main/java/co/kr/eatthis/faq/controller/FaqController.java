package co.kr.eatthis.faq.controller;

import co.kr.eatthis.common.Enum.CategoryType;
import co.kr.eatthis.common.Enum.StatusCode;
import co.kr.eatthis.common.domain.model.PageNavigator;
import co.kr.eatthis.common.domain.model.ResponseData;
import co.kr.eatthis.common.domain.model.ResponseTTTData;
import co.kr.eatthis.common.service.CategoryService;
import co.kr.eatthis.faq.service.FaqService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;


/**
 * 자주묻는 질문
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/faqs")
public class FaqController {

    private final FaqService faqService;
    private final CategoryService categoryService;

    @PostMapping("")
    public ResponseEntity<ResponseData> getFaqList(
            @ModelAttribute PageNavigator pageNavigator
            , @RequestParam(value = "categorySeq", defaultValue = "0") int categorySeq
            , @RequestParam(value = "searchText", defaultValue = "") String searchText) {

        try {
            return ResponseEntity.ok(new ResponseData(faqService.getFaqList(pageNavigator, categorySeq, searchText)));
        } catch (Exception e) {
            return ResponseEntity.ok(new ResponseData(e));
        }
    }
}
