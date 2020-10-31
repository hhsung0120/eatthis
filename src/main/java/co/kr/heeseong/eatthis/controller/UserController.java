package co.kr.heeseong.eatthis.controller;

import co.kr.heeseong.eatthis.domain.user.UserService;
import co.kr.heeseong.eatthis.dto.User;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 유저관련 컨트롤러
 * 로그인, 내정보
 */
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/login")
    public Map<String, Object> userLogin(@ModelAttribute User user){
        return userService.getUserResult(user);
    }

}
