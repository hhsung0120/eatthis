package co.kr.heeseong.eatthis.user.controller;

import co.kr.heeseong.eatthis.common.Enum.ErrorCodeType;
import co.kr.heeseong.eatthis.common.Enum.StatusCode;
import co.kr.heeseong.eatthis.common.domain.model.RequestData;
import co.kr.heeseong.eatthis.common.domain.model.ResponseData;
import co.kr.heeseong.eatthis.common.domain.model.ResponseTTTData;
import co.kr.heeseong.eatthis.common.service.ValidationService;
import co.kr.heeseong.eatthis.common.util.Jwt;
import co.kr.heeseong.eatthis.user.domain.model.AccountUser;
import co.kr.heeseong.eatthis.user.domain.model.Secession;
import co.kr.heeseong.eatthis.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class AccountUserController {

    final ValidationService validationService;
    final UserService userService;

    @PostMapping("/signUp")
    public ResponseEntity<ResponseData> signUp(@RequestBody RequestData requestData) throws Exception {
        try {
            AccountUser accountUser = validationService.validation(requestData, AccountUser.class);
            Long userSeq = userService.insertUser(accountUser);
            return ResponseEntity.ok(new ResponseData("userSeq", userSeq, accountUser));
        } catch (Exception e) {
            //LogUtils.errorLog("signUp exception", e);
            return ResponseEntity.ok(new ResponseData(e));
        }
    }

    @PutMapping("/signUpDetail")
    public ResponseEntity<ResponseData> signUpDetail(@RequestBody RequestData requestData) throws Exception {
        try {
            AccountUser accountUser = validationService.validation(requestData, AccountUser.class);
            userService.updateUser(accountUser);
            return ResponseEntity.ok(new ResponseData("userSeq", "userSeq"));
        } catch (Exception e) {
            //LogUtils.errorLog("signUp exception", e);
            return ResponseEntity.ok(new ResponseData(e));
        }


//        try {
//            Map<String, Object> data = new HashMap<>();
//            data.put("userIdx", "userService.updateUser(accountUser)");
//
//            ResponseTTTData responseData = new ResponseTTTData(
//                    StatusCode.OK.getValue()
//                    , StatusCode.OK.toString()
//                    , data);
//            //return ResponseEntity.ok(responseData);
//        } catch (Exception e) {
//            //return ResponseEntity.ok(new ResponseTTTData(e.getMessage()));
//        }
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseTTTData> login(@RequestBody AccountUser accountUser) {
        log.info("AccountUser Info : {}", "accountUser.getId()");
        try {
            Map<String, Object> data = new HashMap<>();
            data.put("user", "userService.loginProcess(accountUser)");
            data.put("token", Jwt.createToken((AccountUser) data.get("user")));

            ResponseTTTData responseData = new ResponseTTTData(
                    StatusCode.OK.getValue()
                    , StatusCode.OK.toString()
                    , data);
            return ResponseEntity.ok(responseData);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok(new ResponseTTTData(e.getMessage()));
        }
    }

    @GetMapping("")
    public ResponseEntity<ResponseTTTData> users() {
        try {
            Map<String, Object> data = new HashMap<>();
            data.put("user", "userService.getUsers()");

            ResponseTTTData responseData = new ResponseTTTData(
                    StatusCode.OK.getValue()
                    , StatusCode.OK.toString()
                    , data);
            return ResponseEntity.ok(responseData);
        } catch (Exception e) {
            return ResponseEntity.ok(new ResponseTTTData(e.getMessage()));
        }
    }

    @PutMapping("/lunchAlarm")
    public ResponseEntity<ResponseTTTData> lunchAlarm(@RequestBody AccountUser accountUser) {
        try {
//            userService.updateLunchAlarm(accountUser.getLunchAlarm(), accountUser.getLunchAlarmHour(), accountUser.getLunchAlarmMinute());

            ResponseTTTData responseData = new ResponseTTTData(
                    StatusCode.OK.getValue()
                    , StatusCode.OK.toString());
            return ResponseEntity.ok(responseData);
        } catch (Exception e) {
            return ResponseEntity.ok(new ResponseTTTData(e.getMessage()));
        }
    }

    @PutMapping("/dinnerAlarm")
    public ResponseEntity<ResponseTTTData> dinnerAlarm(@RequestBody AccountUser accountUser) {
        try {
            //userService.updateDinnerAlarm(accountUser.getDinnerAlarm(), accountUser.getDinnerAlarmHour(), accountUser.getDinnerAlarmMinute());

            ResponseTTTData responseData = new ResponseTTTData(
                    StatusCode.OK.getValue()
                    , StatusCode.OK.toString());
            return ResponseEntity.ok(responseData);
        } catch (Exception e) {
            return ResponseEntity.ok(new ResponseTTTData(e.getMessage()));
        }
    }

    @PutMapping("/eventAlarm")
    public ResponseEntity<ResponseTTTData> eventAlarm(@RequestBody AccountUser accountUser) {
        try {
            //userService.updateEventAlarm(accountUser.getEventAlarm());

            ResponseTTTData responseData = new ResponseTTTData(
                    StatusCode.OK.getValue()
                    , StatusCode.OK.toString());
            return ResponseEntity.ok(responseData);
        } catch (Exception e) {
            return ResponseEntity.ok(new ResponseTTTData(e.getMessage()));
        }
    }

    @PutMapping("/serviceAlarm")
    public ResponseEntity<ResponseTTTData> setServiceAlarm(@RequestBody AccountUser accountUser) {
        try {
            //userService.updateServiceAlarm(accountUser.getServiceAlarm());

            ResponseTTTData responseData = new ResponseTTTData(
                    StatusCode.OK.getValue()
                    , StatusCode.OK.toString());
            return ResponseEntity.ok(responseData);
        } catch (Exception e) {
            return ResponseEntity.ok(new ResponseTTTData(e.getMessage()));
        }
    }

    @GetMapping("/secession")
    public ResponseEntity<ResponseTTTData> secession() {
        try {
            Map<String, Object> data = new HashMap<>();
            // data.put("list", userService.getSecessionReasonList());
            //data.put("userIdx", userService.getAccountUserIdx());

            ResponseTTTData responseData = new ResponseTTTData(
                    StatusCode.OK.getValue()
                    , StatusCode.OK.toString()
                    , data);
            return ResponseEntity.ok(responseData);
        } catch (Exception e) {
            return ResponseEntity.ok(new ResponseTTTData(e.getMessage()));
        }
    }

    @PostMapping("/secession")
    public ResponseEntity<ResponseTTTData> secession(@RequestBody Secession secession) {
        try {
            //   userService.updateUserStatus(new Secession(secession.getIdx(), secession.getMemo(), ""));

            ResponseTTTData responseData = new ResponseTTTData(
                    StatusCode.OK.getValue()
                    , StatusCode.OK.toString());
            return ResponseEntity.ok(responseData);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.ok(new ResponseTTTData(e.getMessage()));
        } catch (RuntimeException e) {
            e.printStackTrace();
            return ResponseEntity.ok(new ResponseTTTData(ErrorCodeType.INVALID_ARGUMENT.getValue() + "-> " + e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.ok(new ResponseTTTData(ErrorCodeType.ETC_ERROR.getValue() + "-> " + e.getMessage()));
        }
    }

    @GetMapping("/invalidToken")
    public ResponseEntity<ResponseTTTData> invalidToken() {
        ResponseTTTData responseData = new ResponseTTTData(
                StatusCode.SERVER_ERROR.getValue()
                , ErrorCodeType.INVALID_TOKEN.getValue()
        );

        return ResponseEntity.ok(responseData);
    }
}
