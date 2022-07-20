package co.kr.eatthis.user.controller;

import co.kr.eatthis.common.Enum.StatusCode;
import co.kr.eatthis.common.domain.model.ResponseData;
import co.kr.eatthis.common.domain.model.ResponseTTTData;
import co.kr.eatthis.user.domain.model.AccountUser;
import co.kr.eatthis.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class AccountUserAlarmController {

    private final UserService userService;

    @GetMapping("/lunchAlarm/{userSeq}")
    public ResponseEntity<ResponseData> lunchAlarmYnUpdate(@PathVariable Long userSeq) {
        try {
            boolean result = userService.updateLunchAlarmYn(userSeq);
            return ResponseEntity.ok(new ResponseData("result", result));
        } catch (Exception e) {
            return ResponseEntity.ok(new ResponseData(e));
        }
    }
}
