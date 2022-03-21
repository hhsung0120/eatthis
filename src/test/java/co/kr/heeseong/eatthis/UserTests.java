package co.kr.heeseong.eatthis;

import co.kr.heeseong.eatthis.user.domain.model.AccountUser;
import co.kr.heeseong.eatthis.user.service.UserService;
import jdk.jfr.Description;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserTests {

    @Autowired
    private UserService userService;

    @Test
    @Description("회원가입 테스트")
    void signUpTest() throws Exception {
        userService.insertUser(new AccountUser("sdfsdf", "sdfsdf"));
    }

}
