package co.kr.heeseong.eatthis.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Controller
@RequestMapping("/")
public class IndexController {

    @GetMapping("")
    public String index(HttpServletRequest request){
        String redirectUrl = String.valueOf(request.getRequestURL());
        if(redirectUrl.contains("218.238.18.185")){
            redirectUrl = redirectUrl + ":8000";
        }
        return "redirect:"+request.getRequestURL()+"api/description";
    }
}