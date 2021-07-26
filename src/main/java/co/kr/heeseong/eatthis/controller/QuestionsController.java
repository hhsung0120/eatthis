package co.kr.heeseong.eatthis.controller;

import co.kr.heeseong.eatthis.Enum.StatusCode;
import co.kr.heeseong.eatthis.model.Questions;
import co.kr.heeseong.eatthis.model.ResponseData;
import co.kr.heeseong.eatthis.service.FaqService;
import co.kr.heeseong.eatthis.service.QuestionsService;
import lombok.RequiredArgsConstructor;
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

    @PostMapping("/form/{userIdx}")
    public ResponseEntity<ResponseData> form(@PathVariable long userIdx, @RequestBody Questions questions){
        try{
            questions.setUserIdx(userIdx);
            questionsService.saveQuestions(questions);

            ResponseData responseData = new ResponseData(
                    StatusCode.OK.getValue()
                    , StatusCode.OK.toString());
            return ResponseEntity.ok(responseData);
        }catch (Exception e){
            return ResponseEntity.ok(new ResponseData(e.getMessage()));
        }
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

    @GetMapping("/{userIdx}/{questionsIdx}")
    public ResponseEntity<ResponseData> detail(@PathVariable long userIdx
                                    , @PathVariable long questionsIdx){
        try{
            //TODO userIdx 세션이랑 검사해서 아니면 튕겨내기
            //questions.getUserIdx() != session.userIdx

            ResponseData responseData = new ResponseData(
                    StatusCode.OK.getValue()
                    , StatusCode.OK.toString()
                    , questionsService.getQuestions(new Questions(userIdx, questionsIdx)));
            return ResponseEntity.ok(responseData);
        }catch (Exception e){
            return ResponseEntity.ok(new ResponseData(e.getMessage()));
        }
    }
}
