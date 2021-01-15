package co.kr.heeseong.eatthis.controller;

import co.kr.heeseong.eatthis.service.FaqService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/insert")
    public int faqSave(){
        return 3;
    }

    @GetMapping("/{page}")
    public Map<String, Object> faqList(@PathVariable int page){
        Map<String, Object> result = new LinkedHashMap<>();

        try{
            result.put("dataList", faqService.getFaqList(page));
        }catch (Exception e){
            result.put("reason", e.getMessage());
        }

        return result;
    }

}
