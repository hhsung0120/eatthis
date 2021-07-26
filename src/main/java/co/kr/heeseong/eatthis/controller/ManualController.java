package co.kr.heeseong.eatthis.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;

@Slf4j
@Controller
@RestController
@RequiredArgsConstructor
public class ManualController {



    @GetMapping("/store/{userIdx}/{storeIdx}/{menuIdx}/{reviewIdx}/saveReview")
    public Map<String, Object> saveReview(){
        Map<String, Object> result = new LinkedHashMap<>();
        result.put("[request - post]", "");
        result.put("contents", "리뷰내용입니다.");
        result.put("file", "멀티플로 보내고 받을 수 있습니다.");
        result.put("totalPrice", "총 금액");
        result.put("star", "별점");
        result.put("[response]", "");
        result.put("[성공]", "result : success");
        result.put("[실패]", "result : fail, reason : e.getMessage()");
        result.put("", "");
        result.put(" ", "");
        result.put("  ", "");
        result.put("comment", "리뷰 IDX 는 최초 등록 시 0 으로 보내면 됩니다.");
        result.put("TODO", "메뉴 구조가 현재 없어서 없는 메뉴에대한 예외를 처리해야함");
        result.put("TODO", "리뷰 수정 기능이 있었나 ? 기획에..? 현재는 없음 구현 해야함");

        return result;
    }
}
