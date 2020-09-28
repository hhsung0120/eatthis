package co.kr.heeseong.eatthis.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/api")
public class ApiDescriptionController {

    @GetMapping("/description")
    public String mainList(){
        log.info("메인");
        return "index";
    }
}
