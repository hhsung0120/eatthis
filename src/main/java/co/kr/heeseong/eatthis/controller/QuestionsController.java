package co.kr.heeseong.eatthis.controller;

import co.kr.heeseong.eatthis.Enum.EventResultType;
import co.kr.heeseong.eatthis.model.Questions;
import co.kr.heeseong.eatthis.service.FaqService;
import co.kr.heeseong.eatthis.service.QuestionsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("{userIdx}/form")
    public Map<String, Object> form(@PathVariable long userIdx){
        Map<String, Object> result = new LinkedHashMap<>();
        result.put("userIdx", userIdx);
        try{
            result.put("categoryList", faqService.getFaqCategoryList());
        }catch (Exception e){
            result.put("reason", e.getMessage());
        }

        return result;
    }

    @PostMapping("{userIdx}/form")
    public Map<String, Object> form(@PathVariable long userIdx, @ModelAttribute Questions questions){
        Map<String, Object> result = new LinkedHashMap<>();
        try{
            questions.setUserIdx(userIdx);
            result.put("eventResult", questionsService.saveQuestions(questions));
        }catch (Exception e){
            e.printStackTrace();
            result.put("eventResult", EventResultType.FAIL);
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
