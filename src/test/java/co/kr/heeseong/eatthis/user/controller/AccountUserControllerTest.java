package co.kr.heeseong.eatthis.user.controller;

import co.kr.heeseong.eatthis.user.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Description;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers = AccountUserController.class)
public class AccountUserControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private UserService userService;

    @Test
    @Description("회원가입 테스트")
    void signUpTest() throws Exception {
//       mvc.perform(MockMvcRequestBuilders.post("/users/signUp"))
//               .andExpect(MockMvcResultMatchers.status().isOk())
//               .andExpect(MockMvcResultMatchers.content().string("test"));
    }

}
