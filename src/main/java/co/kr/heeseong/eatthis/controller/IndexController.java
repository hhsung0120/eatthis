package co.kr.heeseong.eatthis.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/")
public class IndexController {

    @GetMapping("")
    public String index(HttpServletRequest request){
        return "redirect:"+request.getRequestURL()+"/api/description";
    }
}
