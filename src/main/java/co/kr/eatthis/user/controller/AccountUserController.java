package co.kr.eatthis.user.controller;

import co.kr.eatthis.common.Enum.ErrorCode;
import co.kr.eatthis.common.Enum.StatusCode;
import co.kr.eatthis.common.domain.model.ResponseData;
import co.kr.eatthis.common.domain.model.ResponseTTTData;
import co.kr.eatthis.common.service.ValidationService;
import co.kr.eatthis.common.util.Jwt;
import co.kr.eatthis.user.domain.model.AccountUser;
import co.kr.eatthis.user.domain.model.Secession;
import co.kr.eatthis.user.service.UserService;
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

    private final ValidationService validationService;
    private final UserService userService;

    @PostMapping("/sign-up")
    public ResponseEntity<ResponseData> signUp(@RequestBody Map<String, Object> requestData) {
        log.info("/sign-up request parameter : {}", requestData.toString());

        try {
            AccountUser accountUser = validationService.validation(requestData, AccountUser.class);
            Long userSeq = userService.insertUser(accountUser);
            return ResponseEntity.ok(new ResponseData("userSeq", userSeq, accountUser));
        } catch (Exception e) {
            return ResponseEntity.ok(new ResponseData(e));
        }
    }

    @GetMapping("/nick-name/check")
    public ResponseEntity<ResponseData> nickNameCheck(@RequestBody Map<String, Object> requestData) {
        log.info("/nick-name/check request parameter : {}", requestData.toString());

        try {
            AccountUser accountUser = validationService.validation(requestData, AccountUser.class);
            boolean result = userService.checkNickName(accountUser);
            return ResponseEntity.ok(new ResponseData("result", result));
        } catch (Exception e) {
            return ResponseEntity.ok(new ResponseData(e));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseData> login(@RequestBody Map<String, Object> requestData) {
        log.info("/login request parameter : {}", requestData.toString());

        try {
            AccountUser accountUser = validationService.validation(requestData, AccountUser.class);

            Map<String, Object> data = new HashMap<>();
            data.put("user", userService.loginProcess(accountUser));
            data.put("token", Jwt.createToken((AccountUser) data.get("user")));
            return ResponseEntity.ok(new ResponseData("data", data));
        } catch (Exception e) {
            return ResponseEntity.ok(new ResponseData(e));
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
            return ResponseEntity.ok(new ResponseTTTData(ErrorCode.INVALID_ARGUMENT.getMessageKr() + "-> " + e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.ok(new ResponseTTTData(ErrorCode.ETC_ERROR.getMessageKr() + "-> " + e.getMessage()));
        }
    }

    @GetMapping("/invalid-token")
    public ResponseEntity<ResponseTTTData> invalidToken() {
        ResponseTTTData responseData = new ResponseTTTData(
                StatusCode.SERVER_ERROR.getValue()
                , ErrorCode.INVALID_TOKEN.getMessageKr()
        );

        return ResponseEntity.ok(responseData);
    }
}
