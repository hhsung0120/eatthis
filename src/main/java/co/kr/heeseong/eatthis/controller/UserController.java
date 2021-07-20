package co.kr.heeseong.eatthis.controller;

import co.kr.heeseong.eatthis.Enum.ErrorCodeType;
import co.kr.heeseong.eatthis.Enum.StatusCode;
import co.kr.heeseong.eatthis.model.ResponseData;
import co.kr.heeseong.eatthis.model.Secession;
import co.kr.heeseong.eatthis.model.User;
import co.kr.heeseong.eatthis.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.ConstraintViolationException;
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
            userService.updateLunchAlarm(idx, user.getLunchAlarm(), user.getLunchAlarmHour(), user.getLunchAlarmMinute());

            ResponseData responseData = new ResponseData(
                    StatusCode.OK.getValue()
                    , StatusCode.OK.toString()
                    , "");
            return ResponseEntity.ok(responseData);
        }catch (Exception e){
            return ResponseEntity.ok(new ResponseData(e.getMessage()));
        }
    }

    @PutMapping("/dinnerAlarm/{idx}")
    public ResponseEntity<ResponseData> dinnerAlarm(@PathVariable long idx, @RequestBody User user){
        try{
            userService.updateDinnerAlarm(idx, user.getDinnerAlarm(), user.getDinnerAlarmHour(), user.getDinnerAlarmMinute());

            ResponseData responseData = new ResponseData(
                    StatusCode.OK.getValue()
                    , StatusCode.OK.toString()
                    , "");
            return ResponseEntity.ok(responseData);
        }catch (Exception e){
            return ResponseEntity.ok(new ResponseData(e.getMessage()));
        }
    }

    @PutMapping("/eventAlarm/{idx}")
    public ResponseEntity<ResponseData> eventAlarm(@PathVariable long idx, @RequestBody User user){
        try{
            userService.updateEventAlarm(idx, user.getEventAlarm());

            ResponseData responseData = new ResponseData(
                    StatusCode.OK.getValue()
                    , StatusCode.OK.toString()
                    , "");
            return ResponseEntity.ok(responseData);
        }catch (Exception e){
            return ResponseEntity.ok(new ResponseData(e.getMessage()));
        }
    }

    @PutMapping("/serviceAlarm/{idx}")
    public ResponseEntity<ResponseData> setServiceAlarm(@PathVariable long idx, @RequestBody User user){
        try{
            userService.updateServiceAlarm(idx, user.getServiceAlarm());

            ResponseData responseData = new ResponseData(
                    StatusCode.OK.getValue()
                    , StatusCode.OK.toString()
                    , "");
            return ResponseEntity.ok(responseData);
        }catch (Exception e){
            return ResponseEntity.ok(new ResponseData(e.getMessage()));
        }
    }

    @GetMapping("/secession/{idx}")
    public ResponseEntity<ResponseData> secession(@PathVariable long idx){
        try{
            Map<String, Object> data = new HashMap<>();
            data.put("list", userService.getSecessionReasonList());
            data.put("userIdx", idx);

            ResponseData responseData = new ResponseData(
                    StatusCode.OK.getValue()
                    , StatusCode.OK.toString()
                    , data);
            return ResponseEntity.ok(responseData);
        }catch (Exception e){
            return ResponseEntity.ok(new ResponseData(e.getMessage()));
        }
    }

    @PostMapping("/secession/{idx}")
    public ResponseEntity<ResponseData> secession(@PathVariable long idx, @RequestBody Secession secession){
        try{
            userService.updateUserStatus(new Secession(secession.getIdx(), idx, secession.getMemo()));

            ResponseData responseData = new ResponseData(
                    StatusCode.OK.getValue()
                    , StatusCode.OK.toString()
                    , "");
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
}
