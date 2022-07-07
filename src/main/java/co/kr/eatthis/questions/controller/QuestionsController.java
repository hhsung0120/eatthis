package co.kr.eatthis.questions.controller;

import co.kr.eatthis.common.Enum.StatusCode;
import co.kr.eatthis.common.domain.model.ResponseData;
import co.kr.eatthis.common.domain.model.ResponseTTTData;
import co.kr.eatthis.questions.service.QuestionsService;
import co.kr.eatthis.faq.service.FaqService;
import co.kr.eatthis.questions.domain.model.Questions;
import co.kr.eatthis.user.service.UserService;
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

//    @GetMapping("/form")
//    public ResponseEntity<ResponseData> form() {
//        try {
//            Map<String, Object> data = new HashMap<>();
//            data.put("categoryList", faqService.getFaqCategoryList());
//            //data.put("userIdx", userService.getAccountUserIdx());
//
//            ResponseTTTData responseData = new ResponseTTTData(
//                    StatusCode.OK.getValue()
//                    , StatusCode.OK.toString()
//                    , data);
//            return ResponseEntity.ok(responseData);
//        } catch (Exception e) {
//            return ResponseEntity.ok(new ResponseTTTData(e.getMessage()));
//        }
//    }

//    @GetMapping("/form")
//    public ResponseEntity<ResponseTTTData> form() {
//        try {
//            Map<String, Object> data = new HashMap<>();
//            data.put("categoryList", faqService.getFaqCategoryList());
//            //data.put("userIdx", userService.getAccountUserIdx());
//
//            ResponseTTTData responseData = new ResponseTTTData(
//                    StatusCode.OK.getValue()
//                    , StatusCode.OK.toString()
//                    , data);
//            return ResponseEntity.ok(responseData);
//        } catch (Exception e) {
//            return ResponseEntity.ok(new ResponseTTTData(e.getMessage()));
//        }
//    }

    @PostMapping("/form")
    public ResponseEntity<ResponseTTTData> save(@RequestBody Questions questions) {
        try {
            questionsService.saveQuestions(questions);

            ResponseTTTData responseData = new ResponseTTTData(
                    StatusCode.OK.getValue()
                    , StatusCode.OK.toString());
            return ResponseEntity.ok(responseData);
        } catch (Exception e) {
            return ResponseEntity.ok(new ResponseTTTData(e.getMessage()));
        }
    }

    @GetMapping("")
    public ResponseEntity<ResponseTTTData> questionsList() {
        try {
            ResponseTTTData responseData = new ResponseTTTData(
                    StatusCode.OK.getValue()
                    , StatusCode.OK.toString()
                    , questionsService.getQuestionsList());
            return ResponseEntity.ok(responseData);
        } catch (Exception e) {
            return ResponseEntity.ok(new ResponseTTTData(e.getMessage()));
        }
    }

    @GetMapping("/detail/{questionsIdx}")
    public ResponseEntity<ResponseTTTData> detail(@PathVariable Long questionsIdx) {
        try {
            ResponseTTTData responseData = new ResponseTTTData(
                    StatusCode.OK.getValue()
                    , StatusCode.OK.toString()
                    , questionsService.getQuestionsDetail(questionsIdx));
            return ResponseEntity.ok(responseData);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok(new ResponseTTTData(e.getMessage()));
        }
    }
}
