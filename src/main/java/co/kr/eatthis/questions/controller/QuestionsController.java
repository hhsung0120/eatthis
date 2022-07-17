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

    @GetMapping("")
    public ResponseEntity<ResponseData> list(
            @RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int pageSize, @RequestParam int categorySeq) {

        try {
            Map<String, Object> questionList = questionsService.getQuestionList(page, pageSize, categorySeq);
            return ResponseEntity.ok(new ResponseData(questionList));
        } catch (Exception e) {
            return ResponseEntity.ok(new ResponseData(e));
        }
    }


    @GetMapping("/{questionsSeq}")
    public ResponseEntity<ResponseData> detail(@PathVariable Long questionsSeq) {
        try {
            return ResponseEntity.ok(new ResponseData(questionsService.getQuestionsDetail(questionsSeq)));
        } catch (Exception e) {
            return ResponseEntity.ok(new ResponseData(e));
        }
    }
}
