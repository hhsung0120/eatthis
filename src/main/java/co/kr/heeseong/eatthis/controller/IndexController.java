package co.kr.heeseong.eatthis.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 루트로 들어올 경우 리다이렉트 todo : 설정으로 뺄거임
 */
@Slf4j
@Controller
@RequestMapping("/")
public class IndexController {

    @GetMapping("")
    public String index(){
        return "redirect:/api/description";
    }
}
