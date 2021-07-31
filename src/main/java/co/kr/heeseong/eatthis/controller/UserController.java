package co.kr.heeseong.eatthis.controller;

import co.kr.heeseong.eatthis.Enum.ErrorCodeType;
import co.kr.heeseong.eatthis.Enum.StatusCode;
import co.kr.heeseong.eatthis.model.ResponseData;
import co.kr.heeseong.eatthis.model.Secession;
import co.kr.heeseong.eatthis.model.AccountUser;
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
    public ResponseEntity<ResponseData> login(@RequestBody AccountUser accountUser){
        try{
            Map<String, Object> data = new HashMap<>();
            data.put("user", userService.loginProcess(accountUser));
            data.put("token", Jwt.createToken((AccountUser)data.get("user")));

            ResponseData responseData = new ResponseData(
                    StatusCode.OK.getValue()
                    , StatusCode.OK.toString()
                    , data);
            return ResponseEntity.ok(responseData);
        }catch (Exception e){
            return ResponseEntity.ok(new ResponseData(e.getMessage()));
        }
    }

    @GetMapping("")
    public ResponseEntity<ResponseData> users(){
        try{
            Map<String, Object> data = new HashMap<>();
            data.put("user", userService.getUsers());

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
    public ResponseEntity<ResponseData> signUp(@RequestBody AccountUser accountUser) {
        try{
            Map<String, Object> data = new HashMap<>();
            data.put("userIdx", userService.insertUser(accountUser));

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
    public ResponseEntity<ResponseData> signUpDetail(@RequestBody AccountUser accountUser){
        try{
            Map<String, Object> data = new HashMap<>();
            data.put("userIdx", userService.updateUser(accountUser));

            ResponseData responseData = new ResponseData(
                    StatusCode.OK.getValue()
                    , StatusCode.OK.toString()
                    , data);
            return ResponseEntity.ok(responseData);
        }catch (Exception e){
            return ResponseEntity.ok(new ResponseData(e.getMessage()));
        }
    }

    @PutMapping("/lunchAlarm")
    public ResponseEntity<ResponseData> lunchAlarm(@RequestBody AccountUser accountUser){
        try{
            userService.updateLunchAlarm(accountUser.getLunchAlarm(), accountUser.getLunchAlarmHour(), accountUser.getLunchAlarmMinute());

            ResponseData responseData = new ResponseData(
                    StatusCode.OK.getValue()
                    , StatusCode.OK.toString());
            return ResponseEntity.ok(responseData);
        }catch (Exception e){
            return ResponseEntity.ok(new ResponseData(e.getMessage()));
        }
    }

    @PutMapping("/dinnerAlarm")
    public ResponseEntity<ResponseData> dinnerAlarm(@RequestBody AccountUser accountUser){
        try{
            userService.updateDinnerAlarm(accountUser.getDinnerAlarm(), accountUser.getDinnerAlarmHour(), accountUser.getDinnerAlarmMinute());

            ResponseData responseData = new ResponseData(
                    StatusCode.OK.getValue()
                    , StatusCode.OK.toString());
            return ResponseEntity.ok(responseData);
        }catch (Exception e){
            return ResponseEntity.ok(new ResponseData(e.getMessage()));
        }
    }

    @PutMapping("/eventAlarm")
    public ResponseEntity<ResponseData> eventAlarm(@RequestBody AccountUser accountUser){
        try{
            userService.updateEventAlarm(accountUser.getEventAlarm());

            ResponseData responseData = new ResponseData(
                    StatusCode.OK.getValue()
                    , StatusCode.OK.toString());
            return ResponseEntity.ok(responseData);
        }catch (Exception e){
            return ResponseEntity.ok(new ResponseData(e.getMessage()));
        }
    }

    @PutMapping("/serviceAlarm")
    public ResponseEntity<ResponseData> setServiceAlarm(@RequestBody AccountUser accountUser){
        try{
            userService.updateServiceAlarm(accountUser.getServiceAlarm());

            ResponseData responseData = new ResponseData(
                    StatusCode.OK.getValue()
                    , StatusCode.OK.toString());
            return ResponseEntity.ok(responseData);
        }catch (Exception e){
            return ResponseEntity.ok(new ResponseData(e.getMessage()));
        }
    }

    @GetMapping("/secession")
    public ResponseEntity<ResponseData> secession(){
        try{
            Map<String, Object> data = new HashMap<>();
            data.put("list", userService.getSecessionReasonList());
            data.put("userIdx", userService.getAccountUserIdx());

            ResponseData responseData = new ResponseData(
                    StatusCode.OK.getValue()
                    , StatusCode.OK.toString()
                    , data);
            return ResponseEntity.ok(responseData);
        }catch (Exception e){
            return ResponseEntity.ok(new ResponseData(e.getMessage()));
        }
    }

    @PostMapping("/secession")
    public ResponseEntity<ResponseData> secession(@RequestBody Secession secession){
        try{
            userService.updateUserStatus(new Secession(secession.getIdx(), secession.getMemo(), ""));

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
