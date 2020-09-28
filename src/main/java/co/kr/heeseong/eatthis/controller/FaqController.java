package co.kr.heeseong.eatthis.controller;

import co.kr.heeseong.eatthis.service.FaqService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;



@RestController
@RequiredArgsConstructor
@RequestMapping("/faq")
public class FaqController {

    private final FaqService faqService;

    @GetMapping("/insert")
    public int faqSave(){
        return 3;
    }


    @GetMapping("/list")
    public Map<String, Object> faqList(){
        return new HashMap<>();
    }

}
