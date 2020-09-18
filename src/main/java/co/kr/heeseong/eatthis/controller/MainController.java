package co.kr.heeseong.eatthis.controller;

import co.kr.heeseong.eatthis.service.MainService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Log4j2
@RestController
@RequestMapping("/main")
@RequiredArgsConstructor
public class MainController {

    private final MainService mainService;

    @GetMapping("/list/{locationX}/{locationY}")
    public Map<String, Object> mainList(@PathVariable int locationX, @PathVariable int locationY){
        return mainService.getMainList(locationX, locationY);
    }
}
