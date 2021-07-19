package co.kr.heeseong.eatthis.controller;

import co.kr.heeseong.eatthis.Enum.ErrorCodeType;
import co.kr.heeseong.eatthis.Enum.StatusCode;
import co.kr.heeseong.eatthis.model.ResponseData;
import co.kr.heeseong.eatthis.model.Secession;
import co.kr.heeseong.eatthis.model.User;
import co.kr.heeseong.eatthis.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<ResponseData> login(@RequestBody User user){
        try{
            Map<String, Object> data = new HashMap<>();
            data.put("user", userService.loginProcess(user));

            ResponseData responseData = new ResponseData(
                    StatusCode.OK.getValue()
                    , StatusCode.OK.toString()
                    , data);
            return ResponseEntity.ok(responseData);
        }catch (Exception e){
            return ResponseEntity.ok(new ResponseData(e.getMessage()));
        }
    }

    @GetMapping("/{idx}")
    public ResponseEntity<ResponseData> users(@PathVariable Long idx){
        try{
            Map<String, Object> data = new HashMap<>();
            data.put("user", userService.getUsers(idx));

            ResponseData responseData = new ResponseData(
                    StatusCode.OK.getValue()
                    , StatusCode.OK.toString()
                    , data);
            return ResponseEntity.ok(responseData);
        }catch (Exception e){
            return ResponseEntity.ok(new ResponseData(e.getMessage()));
        }
    }

    @PostMapping("/signUp")
    public ResponseEntity<ResponseData> signUp(@RequestBody User user) {
        try{
            Map<String, Object> data = new HashMap<>();
            data.put("idx", userService.insertUser(user));

            ResponseData responseData = new ResponseData(
                    StatusCode.OK.getValue()
                    , StatusCode.OK.toString()
                    , data);
            return ResponseEntity.ok(responseData);
        }catch(DataIntegrityViolationException e){
            return ResponseEntity.ok(new ResponseData(e.getMessage()));
        }catch (Exception e){
            return ResponseEntity.ok(new ResponseData(ErrorCodeType.ETC_ERROR.getValue()));
        }
    }

    @PutMapping("/signUpDetail")
    public ResponseEntity<ResponseData> signUpDetail(@RequestBody User user){
        try{
            Map<String, Object> data = new HashMap<>();
            data.put("idx", userService.updateUser(user));

            ResponseData responseData = new ResponseData(
                    StatusCode.OK.getValue()
                    , StatusCode.OK.toString()
                    , data);
            return ResponseEntity.ok(responseData);
        }catch (Exception e){
            return ResponseEntity.ok(new ResponseData(e.getMessage()));
        }
    }

    @PutMapping("/lunchAlarm/{idx}")
    public ResponseEntity<ResponseData> lunchAlarm(@PathVariable long idx, @RequestBody User user){
        try{
            Map<String, Object> data = new HashMap<>();

            userService.updateLunchAlarm(idx, user.getLunchAlarm(), user.getLunchAlarmHour(), user.getLunchAlarmMinute());

            ResponseData responseData = new ResponseData(
                    StatusCode.OK.getValue()
                    , StatusCode.OK.toString()
                    , data);
            return ResponseEntity.ok(responseData);
        }catch (Exception e){
            return ResponseEntity.ok(new ResponseData(e.getMessage()));
        }
    }

    @PutMapping("/dinnerAlarm/{idx}")
    public ResponseEntity<ResponseData> dinnerAlarm(@PathVariable long idx, @RequestBody User user){
        try{
            Map<String, Object> data = new HashMap<>();

            userService.updateDinnerAlarm(idx, user.getDinnerAlarm(), user.getDinnerAlarmHour(), user.getDinnerAlarmMinute());

            ResponseData responseData = new ResponseData(
                    StatusCode.OK.getValue()
                    , StatusCode.OK.toString()
                    , data);
            return ResponseEntity.ok(responseData);
        }catch (Exception e){
            return ResponseEntity.ok(new ResponseData(e.getMessage()));
        }
    }

    @PutMapping("/eventAlarm/{idx}")
    public ResponseEntity<ResponseData> eventAlarm(@PathVariable long idx, @RequestBody User user){
        try{
            Map<String, Object> data = new HashMap<>();

            userService.updateEventAlarm(idx, user.getEventAlarm());

            ResponseData responseData = new ResponseData(
                    StatusCode.OK.getValue()
                    , StatusCode.OK.toString()
                    , data);
            return ResponseEntity.ok(responseData);
        }catch (Exception e){
            return ResponseEntity.ok(new ResponseData(e.getMessage()));
        }
    }

    @PutMapping("/serviceAlarm/{idx}")
    public ResponseEntity<ResponseData> setServiceAlarm(@PathVariable long idx, @RequestBody User user){
        try{
            Map<String, Object> data = new HashMap<>();

            userService.updateServiceAlarm(idx, user.getServiceAlarm());

            ResponseData responseData = new ResponseData(
                    StatusCode.OK.getValue()
                    , StatusCode.OK.toString()
                    , data);
            return ResponseEntity.ok(responseData);
        }catch (Exception e){
            return ResponseEntity.ok(new ResponseData(e.getMessage()));
        }
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
