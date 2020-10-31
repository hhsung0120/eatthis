package co.kr.heeseong.eatthis.controller;

import co.kr.heeseong.eatthis.domain.faq.FaqService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


/**
 * 자주묻는 질문
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/faq")
public class FaqController {

    private final FaqService faqService;

    @GetMapping("/insert")
    public int faqSave(){
        return 3;
    }

    @GetMapping("/list/{page}")
    public Map<String, Object> faqList(@PathVariable int page){
        return faqService.getFaqListResult(page);
    }

}
