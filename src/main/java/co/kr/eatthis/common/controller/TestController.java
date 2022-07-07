package co.kr.eatthis.common.controller;

import co.kr.eatthis.common.service.TestService;
import co.kr.eatthis.common.domain.model.ResponseData;
import co.kr.eatthis.common.domain.model.Test;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Controller
@RequestMapping("/test")
@RequiredArgsConstructor
public class TestController {

    private final TestService testService;

    @GetMapping("/insert")
    public String insert() {
        Test test = new Test();
        test.setUserId("아이디");
        test.setUserName("이름");

        Long idx = testService.insertTestUser(test);
        return String.valueOf(idx);
    }

    @GetMapping("/update")
    public String update() {
        Test test = new Test();
        test.setIdx(3L);
        test.setUserId("수정zz");
        test.setUserName("수정이름zzz");

        testService.updateTestUser(test);
        return String.valueOf(test.getIdx());
    }

    @GetMapping("/delete")
    public String delete() {
        Test test = new Test();
        test.setIdx(2L);
        testService.deleteTestUser(test);
        return String.valueOf(test.getIdx());
    }

    @GetMapping("/jenkinsTest")
    public String jenkinsTest() {
        return "젠킨스";
    }

    @GetMapping("/list")
    public List<Test> list() {
        return testService.getTestList();
    }

    @GetMapping("/createToken")
    public String createToken() {
//        AccountUser accountUser = AccountUser.builder().id("setse@set").idx(2).birthday("sdfsdf").build();
//        String to = Jwt.createToken(accountUser);
//        System.out.println(to);
//        try {
//            System.out.println(Jwt.verification(to));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        return "d";
    }

    @GetMapping("/json")
    public ResponseEntity<ResponseData> test() throws Exception {
        try {
            return ResponseEntity.ok(new ResponseData("userSeq", 32));
        } catch (Exception e) {
            return ResponseEntity.ok(new ResponseData(e));
        }
    }


}
