package co.kr.heeseong.eatthis.controller;

import co.kr.heeseong.eatthis.service.FaqService;
import co.kr.heeseong.eatthis.service.QuestionsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 묻고 답하기
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/questions")
public class QuestionsController {

    private final QuestionsService questionsService;
    private final FaqService faqService;

    @GetMapping("/view")
    public Map<String, Object> getView(){
        Map<String, Object> result = new LinkedHashMap<>();
        try{
            result.put("categoryList", faqService.getFaqCategoryList());
        }catch (Exception e){
            e.printStackTrace();
            result.put("reason", e.getMessage());
        }
        return result;
    }

    @GetMapping("/{userIdx}/list")
    public Map<String, Object> questionsList(@PathVariable long userIdx){
        Map<String, Object> result = new LinkedHashMap<>();
        try{
            result.put("data", questionsService.getQuestionsList(userIdx));
        }catch (Exception e){
            result.put("reason", e.getMessage());
        }
        return result;
    }

}
