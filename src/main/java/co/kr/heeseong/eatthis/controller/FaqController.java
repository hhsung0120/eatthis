package co.kr.heeseong.eatthis.controller;

import co.kr.heeseong.eatthis.dto.FaqDto;
import co.kr.heeseong.eatthis.service.FaqService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
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


    @GetMapping("/list/{page}")
    public Map<String, Object> faqList(@PathVariable int page){
        Map<String, Object> result = new HashMap<>();
        List<FaqDto> faqList = faqService.getFaqList(page);
        result.put("faqList", faqList);
        return result;
    }

}
