package co.kr.heeseong.eatthis.questions.controller;

import co.kr.heeseong.eatthis.common.Enum.StatusCode;
import co.kr.heeseong.eatthis.common.model.ResponseData;
import co.kr.heeseong.eatthis.faq.service.FaqService;
import co.kr.heeseong.eatthis.questions.domain.model.Questions;
import co.kr.heeseong.eatthis.questions.service.QuestionsService;
import co.kr.heeseong.eatthis.user.service.UserService;
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
    private final UserService userService;
    private final FaqService faqService;

    @GetMapping("/form")
    public ResponseEntity<ResponseData> form(){
        try{
            Map<String, Object> data = new HashMap<>();
            data.put("categoryList", faqService.getFaqCategoryList());
            data.put("userIdx", userService.getAccountUserIdx());

            ResponseData responseData = new ResponseData(
                    StatusCode.OK.getValue()
                    , StatusCode.OK.toString()
                    , data);
            return ResponseEntity.ok(responseData);
        }catch (Exception e){
            return ResponseEntity.ok(new ResponseData(e.getMessage()));
        }
    }

    @PostMapping("/form")
    public ResponseEntity<ResponseData> save(@RequestBody Questions questions){
        try{
            questionsService.saveQuestions(questions);

            ResponseData responseData = new ResponseData(
                    StatusCode.OK.getValue()
                    , StatusCode.OK.toString());
            return ResponseEntity.ok(responseData);
        }catch (Exception e){
            return ResponseEntity.ok(new ResponseData(e.getMessage()));
        }
    }

    @GetMapping("")
    public ResponseEntity<ResponseData> questionsList(){
        try{
            ResponseData responseData = new ResponseData(
                    StatusCode.OK.getValue()
                    , StatusCode.OK.toString()
                    , questionsService.getQuestionsList());
            return ResponseEntity.ok(responseData);
        }catch (Exception e){
            return ResponseEntity.ok(new ResponseData(e.getMessage()));
        }
    }

    @GetMapping("/detail/{questionsIdx}")
    public ResponseEntity<ResponseData> detail(@PathVariable Long questionsIdx){
        try{
            ResponseData responseData = new ResponseData(
                    StatusCode.OK.getValue()
                    , StatusCode.OK.toString()
                    , questionsService.getQuestionsDetail(questionsIdx));
            return ResponseEntity.ok(responseData);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.ok(new ResponseData(e.getMessage()));
        }
    }
}
