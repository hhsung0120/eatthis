package co.kr.heeseong.eatthis.controller;

import co.kr.heeseong.eatthis.domain.user.UserService;
import co.kr.heeseong.eatthis.dto.User;
import com.sun.xml.bind.v2.TODO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashMap;
import java.util.LinkedHashMap;
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
        } catch (Exception e){
            //TODO : 에러 디테일하게 잡기, 아이디중복인지 진짜 오류인지
            log.info("saveUser exception {}", e.getMessage());
            result.put("reason", "아이디 중복 또는 기타 오류 입니다.");
        }

        return result;
    }

    @GetMapping("/{idx}")
    public Map<String, Object> user(@PathVariable Long idx){
        Map<String, Object> result = new HashMap<>();
        result.put("user", userService.getUser(idx));
        return result;
    }
}
