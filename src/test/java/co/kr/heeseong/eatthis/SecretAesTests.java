package co.kr.heeseong.eatthis;

import co.kr.heeseong.eatthis.common.util.SecretAes;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
public class SecretAesTests {


    @Test
    public void encText() throws Exception {
        Map<String, Object> data = new HashMap<>();
        data.put("userId", "hhsung0120@naver.com");
        data.put("password", "1234");
        data.put("checkPassword", "12345");
        data.put("termsAgree", "Y");
        data.put("privacyAgree", "Y");
        data.put("locationAgree", "n");
        System.out.println("Map : " + data);

        ObjectMapper obj = new ObjectMapper();
        String json = obj.writeValueAsString(data);
        System.out.println("json obj : " + json);

        String encText = SecretAes.encrypt(json);
        System.out.println("encText : " + encText);
        System.out.println("decrypt : " + SecretAes.decrypt(encText));
    }

    @Test
    public void decText() throws Exception {
        String decText = "A_N_-CJY-apgLrV5P5SgOZqXN6g-7_7xQ-NlTelWwlglsfrA2Q1_z911AVnoEnWWqnM5zJeQIMrDBa3rM2M3c4wCakhHHqA5jPZzDt9mOEg";
        System.out.println("decrypt : " + SecretAes.decrypt(decText));
    }
}
