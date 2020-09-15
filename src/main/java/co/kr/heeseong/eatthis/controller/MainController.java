package co.kr.heeseong.eatthis.controller;

import co.kr.heeseong.eatthis.service.MainService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/main")
@AllArgsConstructor
public class MainController {

    private MainService mainService;

    @GetMapping("/list")
    public Map<String, Object> mainList(){
        return mainService.getMainList();
    }
}
