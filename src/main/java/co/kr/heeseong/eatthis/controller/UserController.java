package co.kr.heeseong.eatthis.controller;

import co.kr.heeseong.eatthis.Enum.StatusCode;
import co.kr.heeseong.eatthis.Enum.LoginResultType;
import co.kr.heeseong.eatthis.model.ResponseData;
import co.kr.heeseong.eatthis.model.Secession;
import co.kr.heeseong.eatthis.model.User;
import co.kr.heeseong.eatthis.service.UserService;
import co.kr.heeseong.eatthis.util.HttpHeaderUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 유저관련 컨트롤러
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @PostMapping("/login")
    public Map<String, Object> login(@ModelAttribute User user){
        Map<String, Object> result = new LinkedHashMap<>();

        try{
            result.put("result", userService.loginProsess(user));
        }catch (Exception e){
            result.put("result", LoginResultType.FAIL);
        }

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
    public ResponseEntity<ResponseData> user(@PathVariable Long idx){
        return ResponseEntity.ok(userService.getUser(idx));
    }

    @PostMapping("lunchAlarm/{idx}")
    public Map<String, Object> lunchAlarm(@PathVariable long idx, @ModelAttribute User user){
        Map<String, Object> result = new LinkedHashMap<>();

        try{
            result.put("result", userService.updateLunchAlarm(idx, user.getLunchAlarm(), user.getAlarmTimeHour(), user.getAlarmTimeMinute()));
        }catch (IllegalArgumentException e){
            result.put("result", e.getMessage());
        }catch (Exception e){
            result.put("result", e.getMessage());
        }

        return result;
    }

    @PostMapping("/dinnerAlarm/{idx}")
    public Map<String, Object> dinnerAlarm(@PathVariable long idx, @ModelAttribute User user){
        Map<String, Object> result = new LinkedHashMap<>();

        try{
            result.put("result", userService.updateDinnerAlarm(idx, user.getDinnerAlarm(), user.getAlarmTimeHour(), user.getAlarmTimeMinute()));
        }catch (Exception e){
            result.put("result", e.getMessage());
        }

        return result;
    }

    @PostMapping("/eventAlarm/{idx}")
    public Map<String, Object> eventAlarm(@PathVariable long idx, @ModelAttribute User user){
        Map<String, Object> result = new LinkedHashMap<>();

        try{
            result.put("result", userService.updateEventAlarm(idx, user.getEventAlarm()));
        }catch (Exception e){
            result.put("result", e.getMessage());
        }

        return result;
    }

    @PostMapping("/serviceAlarm/{idx}")
    public Map<String, Object> setServiceAlarm(@PathVariable long idx, @ModelAttribute User user){
        Map<String, Object> result = new LinkedHashMap<>();

        try{
            result.put("result", userService.updateServiceAlarm(idx, user.getServiceAlarm()));
        }catch (Exception e){
            result.put("result", e.getMessage());
        }

        return result;
    }

    @GetMapping("/secession/{idx}")
    public Map<String, Object> secession(@PathVariable long idx){
        Map<String, Object> result = new LinkedHashMap<>();

        try{
            result.put("reasonList", userService.getSecessionReasonList());
            result.put("userIdx", idx);
        }catch (Exception e){
            result.put("exception", e.getMessage());
        }

        return result;
    }

    @PostMapping("/secession/{idx}")
    public Map<String, Object> secession(@PathVariable long idx, @ModelAttribute Secession secession){
        Map<String, Object> result = new LinkedHashMap<>();

        try{
            result.put("result", userService.updateUserStatus(idx, secession));
        }catch (Exception e){
            result.put("result", e.getMessage());
        }

        //TODO 탈퇴 성공 이후에 로그아웃 시켜버려야함
        //내가 처리 해야하나? 화면에서 받은 이후 처리하면 되나 ?
        return result;
    }

}
