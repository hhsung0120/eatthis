package co.kr.heeseong.eatthis.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/")
public class IndexController {

    @GetMapping("")
    public String main() {
        return "redirect:index";
    }

    @GetMapping("/index")
    public Map<String, String> index() {
        Map<String, String> result = new HashMap<>();
        result.put("message", "main");
        return result;
    }
}
