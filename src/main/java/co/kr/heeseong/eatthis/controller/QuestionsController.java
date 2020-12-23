package co.kr.heeseong.eatthis.controller;

import co.kr.heeseong.eatthis.Enum.EventResultType;
import co.kr.heeseong.eatthis.model.Questions;
import co.kr.heeseong.eatthis.service.FaqService;
import co.kr.heeseong.eatthis.service.QuestionsService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
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
        result.put("eventResult", EventResultType.FAIL);

        try{
            questions.setUserIdx(userIdx);
            result.put("eventResult", questionsService.saveQuestions(questions));
        }catch (DataIntegrityViolationException e){
            e.printStackTrace();
            result.put("reason", "필수 값 누락 또는 데이터 형태가 맞지 않습니다.");
        }catch (Exception e){
            result.put("reason", "기타 오류 발생");
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
