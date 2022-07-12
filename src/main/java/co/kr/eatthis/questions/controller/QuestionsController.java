package co.kr.eatthis.questions.controller;

import co.kr.eatthis.common.Enum.CategoryType;
import co.kr.eatthis.common.Enum.StatusCode;
import co.kr.eatthis.common.domain.model.Category;
import co.kr.eatthis.common.domain.model.ResponseData;
import co.kr.eatthis.common.domain.model.ResponseTTTData;
import co.kr.eatthis.common.service.CategoryService;
import co.kr.eatthis.questions.service.QuestionsService;
import co.kr.eatthis.faq.service.FaqService;
import co.kr.eatthis.questions.domain.model.Questions;
import co.kr.eatthis.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 묻고 답하기 1:1
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/questions")
public class QuestionsController {

    private final QuestionsService questionsService;
    private final CategoryService categoryService;

    @GetMapping("/form")
    public ResponseEntity<ResponseData> form() {
        try {
            List<Category> categoryList = categoryService.getCategoryList(CategoryType.QUESTIONS);
            return ResponseEntity.ok(new ResponseData("categoryList", categoryList));
        } catch (Exception e) {
            return ResponseEntity.ok(new ResponseData(e));
        }
    }

    @PostMapping("/form")
    public ResponseEntity<ResponseData> save(@RequestBody Questions questions) {
        try {
            questionsService.saveQuestions(questions);
            return ResponseEntity.ok(new ResponseData());
        } catch (Exception e) {
            return ResponseEntity.ok(new ResponseData(e));
        }
    }
//
//    @GetMapping("/detail/{questionsIdx}")
//    public ResponseEntity<ResponseTTTData> detail(@PathVariable Long questionsIdx) {
//        try {
//            ResponseTTTData responseData = new ResponseTTTData(
//                    StatusCode.OK.getValue()
//                    , StatusCode.OK.toString()
//                    , questionsService.getQuestionsDetail(questionsIdx));
//            return ResponseEntity.ok(responseData);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return ResponseEntity.ok(new ResponseTTTData(e.getMessage()));
//        }
//    }
}
