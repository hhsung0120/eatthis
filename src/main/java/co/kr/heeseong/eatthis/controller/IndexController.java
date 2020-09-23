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
        log.info("request {}", request.getRequestURI());
        log.info("request {}", request.getRequestURL());
        log.info("request {}", request.getServerPort());
        log.info("request {}", request.getServletContext());
        log.info("request {}", request.getServletPath());
        return "redirect:"+request.getRequestURL()+"/api/description";
    }
}
