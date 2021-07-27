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

    @GetMapping("/form/{userIdx}")
    public ResponseEntity<ResponseData> form(@PathVariable Long userIdx){
        try{
            Map<String, Object> data = new HashMap<>();
            data.put("userIdx", userIdx);
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
    public ResponseEntity<ResponseData> form(@PathVariable Long userIdx, @RequestBody Questions questions){
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

    @GetMapping("/{userIdx}")
    public ResponseEntity<ResponseData> questionsList(@PathVariable Long userIdx){
        try{
            ResponseData responseData = new ResponseData(
                    StatusCode.OK.getValue()
                    , StatusCode.OK.toString()
                    , questionsService.getQuestionsList(userIdx));
            return ResponseEntity.ok(responseData);
        }catch (Exception e){
            return ResponseEntity.ok(new ResponseData(e.getMessage()));
        }
    }

    @GetMapping("/{userIdx}/{questionsIdx}")
    public ResponseEntity<ResponseData> detail(@PathVariable Long userIdx
                                    , @PathVariable Long questionsIdx){
        try{
            //TODO userIdx 세션이랑 검사해서 아니면 튕겨내기
            //questions.getUserIdx() != session.userIdx
            Questions questions = Questions.builder()
                                    .userIdx(userIdx)
                                    .idx(questionsIdx)
                                    .build();

            ResponseData responseData = new ResponseData(
                    StatusCode.OK.getValue()
                    , StatusCode.OK.toString()
                    , questionsService.getQuestions(questions));
            return ResponseEntity.ok(responseData);
        }catch (Exception e){
            return ResponseEntity.ok(new ResponseData(e.getMessage()));
        }
    }
}
