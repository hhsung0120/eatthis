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

    @GetMapping("/users/lunchAlarm/{idx}")
    public Map<String, Object> setLunchAlarm(){
        Map<String, Object> result = new LinkedHashMap<>();
        result.put("[request - post]", "점심 알림");
        result.put("lunchAlarm", "Y, N");
        result.put("alarmTimeHour", "0~23");
        result.put("alarmTimeMinute", "0~59");
        result.put("[성공]", "result : SUCCESS");
        result.put("[실패]", "result : e.getMessage()");
        result.put("    ", "");
        result.put("     ", "");
        result.put("      ", "");
        result.put("comment", "lunchAlarm 하고 시간은 무조건 같이 보내주세요 Y면 시간도 업데이트 되고 N 이면 안될거라서요");

        return result;
    }

    @GetMapping("/users/dinnerAlarm/{idx}")
    public Map<String, Object> setDinnerAlarm(){
        Map<String, Object> result = new LinkedHashMap<>();
        result.put("[request - post]", "저녁 알림");
        result.put("dinnerAlarm", "Y, N");
        result.put("alarmTimeHour", "0~23");
        result.put("alarmTimeMinute", "0~59");
        result.put("[성공]", "result : SUCCESS");
        result.put("[실패]", "result : e.getMessage()");
        result.put("    ", "");
        result.put("     ", "");
        result.put("      ", "");
        result.put("comment", "dinnerAlarm 하고 시간은 무조건 같이 보내주세요 Y면 시간도 업데이트 되고 N 이면 안될거라서요");

        return result;
    }

    @GetMapping("/users/eventAlarm/{idx}")
    public Map<String, Object> setEventAlarm(){
        Map<String, Object> result = new LinkedHashMap<>();
        result.put("[request - post]", "이벤트 알림");
        result.put("eventAlarm", "Y, N");
        result.put("[성공]", "result : SUCCESS");
        result.put("[실패]", "result : e.getMessage()");

        return result;
    }

    @GetMapping("/users/serviceAlarm/{idx}")
    public Map<String, Object> setServiceAlarm(){
        Map<String, Object> result = new LinkedHashMap<>();
        result.put("[request - post]", "서비스 알림");
        result.put("serviceAlarm", "Y, N");
        result.put("[성공]", "result : SUCCESS");
        result.put("[실패]", "result : e.getMessage()");

        return result;
    }

    @GetMapping("/users/secession/manual")
    public Map<String, Object> secession(){
        Map<String, Object> result = new LinkedHashMap<>();
        result.put("[request - post]", "");
        result.put("idx", "탈퇴 사유 IDX 입니다.");
        result.put("memo", "사유가 기타면 보내주시면 됩니다.");
        result.put("[response]", "");
        result.put("[성공]", "result : SUCCESS");
        result.put("[실패]", "result : e.getMessage()");
        result.put("    ", "");
        result.put("     ", "");
        result.put("      ", "");
        result.put("comment", "if result != SUCCESS 다 실패라서 실패 처리하시면 되고 성공 이후에 로그아웃 시켜주세요");
        result.put("TODO", "회원 탈퇴한 회원 로그인 못하게 해야함");


        return result;
    }

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

    @GetMapping("/questions/save/manual")
    public Map<String, Object> saveQuestions(){
        Map<String, Object> result = new LinkedHashMap<>();
        result.put("[request - post]", "");
        result.put("categoryIdx", "카테고리idx.");
        result.put("questions", "질문 내용");
        result.put("phone", "핸드폰 번호");
        result.put("email", "이메일");
        result.put("[response]", "");
        result.put("[성공]", "result : SUCCESS");
        result.put("[실패]", "result : FAIL, reason : 사유");
        result.put("", "");
        result.put(" ", "");
        result.put("  ", "");
        result.put("comment", "SUCCESS 가 아니면 무조건 reason 보여주면 될듯 합니다.");

        return result;
    }

}
