package co.kr.heeseong.eatthis.controller;

import co.kr.heeseong.eatthis.Enum.LoginResultType;
import co.kr.heeseong.eatthis.domain.user.UserDetailEntity;
import co.kr.heeseong.eatthis.domain.user.UserService;
import co.kr.heeseong.eatthis.dto.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.DatatypeConverterInterface;
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

    @PostMapping("/signUp")
    public Map<String, Object> signUp(@ModelAttribute User user){
        Map<String, Object> result = new LinkedHashMap<>();
        result.put("userIdx", 0);

        //TODO : 에러 디테일하게 잡기, 아이디중복인지 진짜 오류인지
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
        result.put("user", userService.getUser(idx));
        return result;
    }

    @GetMapping("/login")
    public Map<String, Object> login(){
        Map<String, Object> result = new LinkedHashMap<>();

        result.put("[request - post]", "");
        result.put("id", "hhsung0120@naver.com");
        result.put("password", "1234");
        result.put("[response]", "");
        result.put("[성공시]", "loginResult : SUCCESS");
        result.put("[실패시]", "loginResult : USER_NOT_FOUND, INVALID_PASSWORD, FAIL");
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

    @PutMapping("/{idx}/setFoodAlarm")
    public Map<String, Object> setFoodAlarm(@ModelAttribute User user){
        Map<String, Object> result = new HashMap<>();
        try{
            result.put("updateResult", userService.updateFoodAlarm(user.getIdx(), user.getFoodAlarm()));
        }catch (Exception e){
            result.put("updateResult", e.getMessage());
        }
        return result;
    }

    @PutMapping("/{idx}/setEventAlarm")
    public Map<String, Object> setEventAlarm(@ModelAttribute User user){
        Map<String, Object> result = new HashMap<>();
        try{
            result.put("updateResult", userService.updateEventAlarm(user.getIdx(), user.getEventAlarm()));
        }catch (Exception e){
            result.put("updateResult", e.getMessage());
        }
        return result;
    }

    @PutMapping("/{idx}/setServiceAlarm")
    public Map<String, Object> setServiceAlarm(@ModelAttribute User user){
        Map<String, Object> result = new HashMap<>();
        try{
            result.put("updateResult", userService.updateServiceAlarm(user.getIdx(), user.getServiceAlarm()));
        }catch (Exception e){
            result.put("updateResult", e.getMessage());
        }
        return result;
    }


}
