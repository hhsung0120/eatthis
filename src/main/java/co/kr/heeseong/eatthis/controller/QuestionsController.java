package co.kr.heeseong.eatthis.controller;

import co.kr.heeseong.eatthis.domain.questions.QuestionsService;
import co.kr.heeseong.eatthis.dto.QuestionsDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 묻고 답하기
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/questions")
public class QuestionsController {

    private final QuestionsService questionsService;

    @GetMapping("/list/{page}")
    public Map<String, Object> questionsList(@PathVariable int page){
        Map<String, Object> result = new HashMap<>();
        List<QuestionsDto> questionsList = questionsService.getQuestionsList(page);
        result.put("questionsList", questionsList);
        return result;
    }

}
