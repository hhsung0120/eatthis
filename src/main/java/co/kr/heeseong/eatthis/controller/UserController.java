package co.kr.heeseong.eatthis.controller;

import co.kr.heeseong.eatthis.Enum.LoginResultType;
import co.kr.heeseong.eatthis.service.UserService;
import co.kr.heeseong.eatthis.model.Secession;
import co.kr.heeseong.eatthis.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;
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

    @PostMapping("/login")
    public Map<String, Object> login(@ModelAttribute User user){
        Map<String, Object> result = new LinkedHashMap<>();
        try{
            result.put("loginResult", userService.loginProsess(user));
        }catch (Exception e){
            result.put("loginResult", LoginResultType.FAIL);
        }

        return result;
    }

    @GetMapping("/{idx}")
    public Map<String, Object> user(@PathVariable Long idx){
        Map<String, Object> result = new LinkedHashMap<>();
        try{
            result.put("user", userService.getUser(idx));
        }catch (Exception e) {
            result.put("reason", e.getMessage());
        }
        return result;
    }

    @PostMapping("/{idx}/setLunchAlarm")
    public Map<String, Object> setLunchAlarm(@PathVariable long idx, @ModelAttribute User user){
        Map<String, Object> result = new LinkedHashMap<>();
        try{
            result.put("updateResult", userService.updateLunchAlarm(idx, user.getLunchAlarm(), user.getAlarmTimeHour(), user.getAlarmTimeMinute()));
        }catch (IllegalArgumentException e){
            result.put("updateResult", e.getMessage());
        }catch (Exception e){
            result.put("updateResult", e.getMessage());
        }

        return result;
    }

    @PostMapping("/{idx}/setDinnerAlarm")
    public Map<String, Object> setDinnerAlarm(@PathVariable long idx, @ModelAttribute User user){
        Map<String, Object> result = new LinkedHashMap<>();
        try{
            result.put("updateResult", userService.updateDinnerAlarm(idx, user.getDinnerAlarm(), user.getAlarmTimeHour(), user.getAlarmTimeMinute()));
        }catch (Exception e){
            result.put("updateResult", e.getMessage());
        }

        return result;
    }

    @PostMapping("/{idx}/setEventAlarm")
    public Map<String, Object> setEventAlarm(@PathVariable long idx, @ModelAttribute User user){
        Map<String, Object> result = new LinkedHashMap<>();
        try{
            result.put("updateResult", userService.updateEventAlarm(idx, user.getEventAlarm()));
        }catch (Exception e){
            result.put("updateResult", e.getMessage());
        }

        return result;
    }

    @PostMapping("/{idx}/setServiceAlarm")
    public Map<String, Object> setServiceAlarm(@PathVariable long idx, @ModelAttribute User user){
        Map<String, Object> result = new LinkedHashMap<>();
        try{
            result.put("updateResult", userService.updateServiceAlarm(idx, user.getServiceAlarm()));
        }catch (Exception e){
            result.put("updateResult", e.getMessage());
        }

        return result;
    }

    @GetMapping("/secession")
    public Map<String, Object> secession(){
        Map<String, Object> result = new LinkedHashMap<>();

        List<Secession> list = userService.getSecessionReasonList();

        return result;
    }

    @PostMapping("/{idx}/secession")
    public Map<String, Object> secession(@PathVariable long idx, @ModelAttribute User user){
        Map<String, Object> result = new LinkedHashMap<>();
        try{
            result.put("updateResult", userService.updateServiceAlarm(idx, user.getServiceAlarm()));
        }catch (Exception e){
            result.put("updateResult", e.getMessage());
        }

        return result;
    }

}
