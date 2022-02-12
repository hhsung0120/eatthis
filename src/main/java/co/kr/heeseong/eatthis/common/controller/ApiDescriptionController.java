package co.kr.heeseong.eatthis.common.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/api")
public class ApiDescriptionController {

    @GetMapping("/description")
    public String mainList() {
        return "index";
    }
}
