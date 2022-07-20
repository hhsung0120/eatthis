package co.kr.eatthis.user.controller;

import co.kr.eatthis.common.Enum.ErrorCode;
import co.kr.eatthis.common.Enum.StatusCode;
import co.kr.eatthis.common.domain.model.ResponseData;
import co.kr.eatthis.common.domain.model.ResponseTTTData;
import co.kr.eatthis.common.util.Jwt;
import co.kr.eatthis.user.domain.model.AccountUser;
import co.kr.eatthis.user.domain.model.UserSecession;
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

    private final UserService userService;

    @PostMapping("/sign-up")
    public ResponseEntity<ResponseData> signUp(@ModelAttribute AccountUser accountUser) {
        log.info("/sign-up request parameter : {}", accountUser.toString());

        try {
            Long userSeq = userService.insertUser(accountUser);
            return ResponseEntity.ok(new ResponseData("userSeq", userSeq, accountUser));
        } catch (Exception e) {
            return ResponseEntity.ok(new ResponseData(e));
        }
    }

    @PostMapping(value = "/nick-name/check")
    public ResponseEntity<ResponseData> nickNameCheck(@ModelAttribute AccountUser accountUser) {
        log.info("/nick-name/check request parameter : {}", accountUser.getNickName());

        try {
            boolean result = userService.checkNickName(accountUser.getNickName());
            return ResponseEntity.ok(new ResponseData("result", result));
        } catch (Exception e) {
            return ResponseEntity.ok(new ResponseData(e));
        }
    }

    @PostMapping(value = "/login")
    public ResponseEntity<ResponseData> login(@ModelAttribute AccountUser accountUser) {
        log.info("/login request parameter : {}", accountUser.toString());

        try {
            Map<String, Object> data = new HashMap<>();
            data.put("user", userService.loginProcess(accountUser));
            data.put("token", Jwt.createToken((AccountUser) data.get("user")));
            return ResponseEntity.ok(new ResponseData("data", data));
        } catch (Exception e) {
            return ResponseEntity.ok(new ResponseData(e));
        }
    }

    @GetMapping(value = "/secession")
    public ResponseEntity<ResponseData> userSecessionForm() {
        try {
            return ResponseEntity.ok(new ResponseData("list", userService.getSecessionList()));
        } catch (Exception e) {
            return ResponseEntity.ok(new ResponseData(e));
        }
    }

    @PostMapping(value = "/secession")
    public ResponseEntity<ResponseData> userSecessionSave(@ModelAttribute UserSecession userSecession) {
        try {
            boolean result = userService.insertUserSecession(userSecession);
            return ResponseEntity.ok(new ResponseData("result", result));
        } catch (Exception e) {
            return ResponseEntity.ok(new ResponseData(e));
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
