package co.kr.heeseong.eatthis.controller;

import co.kr.heeseong.eatthis.Enum.EventResultType;
import co.kr.heeseong.eatthis.Enum.StatusCode;
import co.kr.heeseong.eatthis.model.Faq;
import co.kr.heeseong.eatthis.model.FaqCategory;
import co.kr.heeseong.eatthis.model.Questions;
import co.kr.heeseong.eatthis.model.ResponseData;
import co.kr.heeseong.eatthis.service.FaqService;
import co.kr.heeseong.eatthis.service.QuestionsService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 묻고 답하기 1:1
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/questions")
public class QuestionsController {

    private final QuestionsService questionsService;
    private final FaqService faqService;

    @GetMapping("/form/{idx}")
    public ResponseEntity<ResponseData> form(@PathVariable long idx){
        try{
            Map<String, Object> data = new HashMap<>();
            data.put("idx", idx);
            data.put("categoryList", faqService.getFaqCategoryList());

            ResponseData responseData = new ResponseData(
                    StatusCode.OK.getValue()
                    , StatusCode.OK.toString()
                    , data);
            return ResponseEntity.ok(responseData);
        }catch (Exception e){
            return ResponseEntity.ok(new ResponseData(e.getMessage()));
        }
    }

    @PostMapping("/form/{idx}")
    public Map<String, Object> form(@PathVariable long idx, @ModelAttribute Questions questions){
        Map<String, Object> result = new LinkedHashMap<>();
        result.put("result", EventResultType.FAIL);

        try{
            questions.setUserIdx(idx);
            result.put("result", questionsService.saveQuestions(questions));
        }catch (DataIntegrityViolationException e){
            e.printStackTrace();
            result.put("reason", "필수 값 누락 또는 데이터 형태가 맞지 않습니다.");
        }catch (Exception e){
            result.put("reason", "기타 오류 발생");
        }

        return result;
    }

    @GetMapping("/{idx}")
    public Map<String, Object> questionsList(@PathVariable long idx){
        Map<String, Object> result = new LinkedHashMap<>();
        try{
            result.put("data", questionsService.getQuestionsList(idx));
        }catch (Exception e){
            result.put("reason", e.getMessage());
        }

        return result;
    }

    @GetMapping("/{idx}/{questionsIdx}")
    public Map<String, Object> detail(@PathVariable long idx
                                    , @PathVariable long questionsIdx){
        Map<String, Object> result = new LinkedHashMap<>();
        try{
            Questions questions = new Questions();
            questions.setUserIdx(idx);
            questions.setIdx(questionsIdx);
            result.put("data", questionsService.getQuestions(questions));
        }catch (Exception e){
            result.put("reason", e.getMessage());
        }
        return result;
    }
}
