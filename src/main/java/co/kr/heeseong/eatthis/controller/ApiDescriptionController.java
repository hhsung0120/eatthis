package co.kr.heeseong.eatthis.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ApiDescriptionController {

    @GetMapping("/description")
    public Map<String, Object> mainList(){
        Map<String, Object> result = new HashMap<String, Object>();

        result.put("[GET] /main/list","메인 리스트");
        result.put("[GET] /user/myPage/{userIdx}","마이페이지");

        return result;
    }
}
