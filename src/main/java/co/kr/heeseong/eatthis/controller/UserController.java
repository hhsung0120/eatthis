package co.kr.heeseong.eatthis.controller;

import co.kr.heeseong.eatthis.Enum.LoginResultType;
import co.kr.heeseong.eatthis.domain.user.UserService;
import co.kr.heeseong.eatthis.dto.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 유저관련 컨트롤러
 * 로그인, 내정보
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @GetMapping("/signUp")
    public Map<String, Object> signUp(){
        Map<String, Object> result = new LinkedHashMap<>();
        result.put("[request1 - post]", "회원가입 1");
        result.put("id", "hhsung0120@naver.com");
        result.put("termsAgree", "Y");
        result.put("privacyAgree", "Y");
        result.put("locationAgree", "Y");
        result.put("signUpType", "DEFAULTE, FACEBOOK, KAKAO");
        result.put("[response]", "");
        result.put("[성공]", "userIdx : 70");
        result.put("[실패]", "userIdx : 0");
        result.put("[실패]", "reason : 중복 된 아이디 입니다. -> hhsung0120@naver.com");

        result.put("", "");
        result.put(" ", "");
        result.put("  ", "");
        result.put("   ", "");

        result.put("[request2 - post]", "회원가입 2");
        result.put("idx", "1");
        result.put("nickName", "닉네임");
        result.put("gender", "MALE, FEMALE");
        result.put("birthday", "1992-01-20");
        result.put("profileImagePath", "https://naver.com");
        result.put("[성공]", "userIdx : 1");
        result.put("[실패]", "userIdx : 0");
        result.put("[실패]", "reason : 유저가 존재하지 않습니다. -> 4421");
        result.put("    ", "");
        result.put("     ", "");
        result.put("      ", "");
        result.put("comment", "if userIdx > 0 성공으로 보면되고 else reason 출력하시면 됩니다. ");

        return result;
    }

    @PostMapping("/signUp")
    public Map<String, Object> signUp(@ModelAttribute User user){
        Map<String, Object> result = new LinkedHashMap<>();
        result.put("userIdx", 0);

        try {
            result.put("userIdx", userService.saveUser(user));
        } catch (DataIntegrityViolationException e){
            result.put("reason", e.getMessage());
        } catch (IllegalArgumentException e){
            result.put("reason", e.getMessage());
        } catch (Exception e){
            result.put("reason", "기타 오류 입니다. 관리자에게 문의하세요");
        }

        return result;
    }

    @GetMapping("/{idx}")
    public Map<String, Object> user(@PathVariable Long idx){
        Map<String, Object> result = new HashMap<>();
        try{
            result.put("user", userService.getUser(idx));
        }catch (Exception e) {
            result.put("reason", e.getMessage());
        }
        return result;
    }

    @GetMapping("/login")
    public Map<String, Object> login(){
        Map<String, Object> result = new LinkedHashMap<>();
        result.put("[request - post]", "");
        result.put("id", "hhsung0120@naver.com");
        result.put("password", "1234");
        result.put("[response]", "");
        result.put("[성공]", "loginResult : SUCCESS");
        result.put("[실패]", "loginResult : USER_NOT_FOUND, INVALID_PASSWORD, FAIL");

        return result;
    }

    @PostMapping("/login")
    public Map<String, Object> login(@ModelAttribute User user){
        Map<String, Object> result = new HashMap<>();
        try{
            result.put("loginResult", userService.loginProsess(user));
        }catch (Exception e){
            result.put("loginResult", LoginResultType.FAIL);
        }
        return result;
    }

    @PutMapping("/{idx}/setLunchAlarm")
    public Map<String, Object> setFoodLunchAlarm(@PathVariable long idx, @ModelAttribute User user){
        Map<String, Object> result = new HashMap<>();

        try{
            result.put("updateResult", userService.updateLunchAlarm(idx, user.getLunchAlarm(), user.getAlarmTimeHour(), user.getAlarmTimeMinute()));
        }catch (IllegalArgumentException e){
            result.put("updateResult", e.getMessage());
        }catch (Exception e){
            result.put("updateResult", e.getMessage());
        }
        return result;
    }

    @PutMapping("/{idx}/setDinnerAlarm")
    public Map<String, Object> setFoodDinnerAlarm(@PathVariable long idx, @ModelAttribute User user){
        Map<String, Object> result = new HashMap<>();
        try{
            result.put("updateResult", userService.updateDinnerAlarm(idx, user.getDinnerAlarm(), user.getAlarmTimeHour(), user.getAlarmTimeMinute()));
        }catch (Exception e){
            result.put("updateResult", e.getMessage());
        }
        return result;
    }

    @PutMapping("/{idx}/setEventAlarm")
    public Map<String, Object> setEventAlarm(@PathVariable long idx, @ModelAttribute User user){
        Map<String, Object> result = new HashMap<>();
        try{
            result.put("updateResult", userService.updateEventAlarm(idx, user.getEventAlarm()));
        }catch (Exception e){
            result.put("updateResult", e.getMessage());
        }
        return result;
    }

    @PutMapping("/{idx}/setServiceAlarm")
    public Map<String, Object> setServiceAlarm(@PathVariable long idx, @ModelAttribute User user){
        Map<String, Object> result = new HashMap<>();
        try{
            result.put("updateResult", userService.updateServiceAlarm(idx, user.getServiceAlarm()));
        }catch (Exception e){
            result.put("updateResult", e.getMessage());
        }
        return result;
    }
}
