package co.kr.heeseong.eatthis.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 루트로 들어올 경우 리다이렉트 todo : 설정으로 뺄거임
 */
@Controller
@RequestMapping("/")
public class IndexController {

    @GetMapping("")
    public String index(){
        return "redirect:/api/description";
    }
}
