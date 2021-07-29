package co.kr.heeseong.eatthis.controller;

import co.kr.heeseong.eatthis.Enum.ErrorCodeType;
import co.kr.heeseong.eatthis.Enum.StatusCode;
import co.kr.heeseong.eatthis.model.ResponseData;
import co.kr.heeseong.eatthis.model.Secession;
import co.kr.heeseong.eatthis.model.User;
import co.kr.heeseong.eatthis.service.UserService;
import co.kr.heeseong.eatthis.util.Jwt;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
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
            data.put("token", Jwt.createToken((User)data.get("user")));

            ResponseData responseData = new ResponseData(
                    StatusCode.OK.getValue()
                    , StatusCode.OK.toString()
                    , data);
            return ResponseEntity.ok(responseData);
        }catch (Exception e){
            return ResponseEntity.ok(new ResponseData(e.getMessage()));
        }
    }

    @GetMapping("/{userIdx}")
    public ResponseEntity<ResponseData> users(@PathVariable Long userIdx){
        try{
            Map<String, Object> data = new HashMap<>();
            data.put("user", userService.getUsers(userIdx));

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
            data.put("userIdx", userService.insertUser(user));

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
            data.put("userIdx", userService.updateUser(user));

            ResponseData responseData = new ResponseData(
                    StatusCode.OK.getValue()
                    , StatusCode.OK.toString()
                    , data);
            return ResponseEntity.ok(responseData);
        }catch (Exception e){
            return ResponseEntity.ok(new ResponseData(e.getMessage()));
        }
    }

    @PutMapping("/lunchAlarm/{userIdx}")
    public ResponseEntity<ResponseData> lunchAlarm(@PathVariable long userIdx, @RequestBody User user){
        try{
            userService.updateLunchAlarm(userIdx, user.getLunchAlarm(), user.getLunchAlarmHour(), user.getLunchAlarmMinute());

            ResponseData responseData = new ResponseData(
                    StatusCode.OK.getValue()
                    , StatusCode.OK.toString());
            return ResponseEntity.ok(responseData);
        }catch (Exception e){
            return ResponseEntity.ok(new ResponseData(e.getMessage()));
        }
    }

    @PutMapping("/dinnerAlarm/{userIdx}")
    public ResponseEntity<ResponseData> dinnerAlarm(@PathVariable long userIdx, @RequestBody User user){
        try{
            userService.updateDinnerAlarm(userIdx, user.getDinnerAlarm(), user.getDinnerAlarmHour(), user.getDinnerAlarmMinute());

            ResponseData responseData = new ResponseData(
                    StatusCode.OK.getValue()
                    , StatusCode.OK.toString());
            return ResponseEntity.ok(responseData);
        }catch (Exception e){
            return ResponseEntity.ok(new ResponseData(e.getMessage()));
        }
    }

    @PutMapping("/eventAlarm/{userIdx}")
    public ResponseEntity<ResponseData> eventAlarm(@PathVariable long userIdx, @RequestBody User user){
        try{
            userService.updateEventAlarm(userIdx, user.getEventAlarm());

            ResponseData responseData = new ResponseData(
                    StatusCode.OK.getValue()
                    , StatusCode.OK.toString());
            return ResponseEntity.ok(responseData);
        }catch (Exception e){
            return ResponseEntity.ok(new ResponseData(e.getMessage()));
        }
    }

    @PutMapping("/serviceAlarm/{userIdx}")
    public ResponseEntity<ResponseData> setServiceAlarm(@PathVariable long userIdx, @RequestBody User user){
        try{
            userService.updateServiceAlarm(userIdx, user.getServiceAlarm());

            ResponseData responseData = new ResponseData(
                    StatusCode.OK.getValue()
                    , StatusCode.OK.toString());
            return ResponseEntity.ok(responseData);
        }catch (Exception e){
            return ResponseEntity.ok(new ResponseData(e.getMessage()));
        }
    }

    @GetMapping("/secession/{userIdx}")
    public ResponseEntity<ResponseData> secession(@PathVariable long userIdx){
        try{
            Map<String, Object> data = new HashMap<>();
            data.put("list", userService.getSecessionReasonList());
            data.put("userIdx", userIdx);

            ResponseData responseData = new ResponseData(
                    StatusCode.OK.getValue()
                    , StatusCode.OK.toString()
                    , data);
            return ResponseEntity.ok(responseData);
        }catch (Exception e){
            return ResponseEntity.ok(new ResponseData(e.getMessage()));
        }
    }

    @PostMapping("/secession/{userIdx}")
    public ResponseEntity<ResponseData> secession(@PathVariable long userIdx, @RequestBody Secession secession){
        try{
            userService.updateUserStatus(new Secession(secession.getIdx(), userIdx, secession.getMemo()));

            ResponseData responseData = new ResponseData(
                    StatusCode.OK.getValue()
                    , StatusCode.OK.toString());
            return ResponseEntity.ok(responseData);
        }catch(IllegalArgumentException e){
            return ResponseEntity.ok(new ResponseData(e.getMessage()));
        }catch (RuntimeException e){
            e.printStackTrace();
            return ResponseEntity.ok(new ResponseData(ErrorCodeType.INVALID_ARGUMENT.getValue() + "-> " + e.getMessage()));
        }catch (Exception e){
            return ResponseEntity.ok(new ResponseData(ErrorCodeType.ETC_ERROR.getValue() + "-> " + e.getMessage()));
        }
    }

    @GetMapping("/invalidToken")
    public ResponseEntity<ResponseData> invalidToken(){
        ResponseData responseData = new ResponseData(
                StatusCode.SERVER_ERROR.getValue()
                , ErrorCodeType.INVALID_TOKEN.getValue()
        );
        return ResponseEntity.ok(responseData);
    }
}
