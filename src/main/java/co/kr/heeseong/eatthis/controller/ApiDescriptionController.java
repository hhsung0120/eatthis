package co.kr.heeseong.eatthis.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api")
public class ApiDescriptionController {

    @GetMapping("/description")
    public String mainList(){
        return "index";
    }
}
