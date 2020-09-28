package co.kr.heeseong.eatthis.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/faq")
public class FaqController {

    @GetMapping("/list")
    public Map<String, Object> faqList(){
        Map<String, Object> result =
        return result;
    }

}
