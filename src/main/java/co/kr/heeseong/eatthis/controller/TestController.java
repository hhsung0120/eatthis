package co.kr.heeseong.eatthis.controller;

import co.kr.heeseong.eatthis.dto.TestDto;
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
        TestDto test = new TestDto();
        test.setUserId("아이디");
        test.setUserName("이름");

        Long idx = testService.insertTestUser(test);
        return String.valueOf(idx);
    }

    @GetMapping("/update")
    public String update(){
        TestDto test = new TestDto();
        test.setIdx(3L);
        test.setUserId("수정zz");
        test.setUserName("수정이름zzz");

        testService.updateTestUser(test);
        return String.valueOf(test.getIdx());
    }

    @GetMapping("/delete")
    public String delete(){
        TestDto test = new TestDto();
        test.setIdx(2L);
        testService.deleteTestUser(test);
        return String.valueOf(test.getIdx());
    }

    @GetMapping("/list")
    public List<TestDto> list(){
        return testService.getTestList();
    }
}
