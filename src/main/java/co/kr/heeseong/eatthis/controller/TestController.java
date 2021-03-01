package co.kr.heeseong.eatthis.controller;

import co.kr.heeseong.eatthis.model.Test;
import co.kr.heeseong.eatthis.service.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class TestController {

    private final TestService testService;

    @GetMapping("/insert")
    public String insert(){
        Test test = new Test();
        test.setUserId("아이디");
        test.setUserName("이름");

        Long idx = testService.insertTestUser(test);
        return String.valueOf(idx);
    }

    @GetMapping("/update")
    public String update(){
        Test test = new Test();
        test.setIdx(3L);
        test.setUserId("수정zz");
        test.setUserName("수정이름zzz");

        testService.updateTestUser(test);
        return String.valueOf(test.getIdx());
    }

    @GetMapping("/delete")
    public String delete(){
        Test test = new Test();
        test.setIdx(2L);
        testService.deleteTestUser(test);
        return String.valueOf(test.getIdx());
    }

    @GetMapping("/jenkinsTest")
    public String jenkinsTest(){
        return "젠킨스";
    }

    @GetMapping("/list")
    public List<Test> list(){
        return testService.getTestList();
    }
}
