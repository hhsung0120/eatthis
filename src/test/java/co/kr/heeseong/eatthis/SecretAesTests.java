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

        Map<String, String> agreeMap = new HashMap<>();
        agreeMap.put("terms", "y");
        agreeMap.put("privacy", "y");
        agreeMap.put("location", "y");
        data.put("agreeMap", agreeMap);

        Map<String, String> agree = (Map<String, String>)data.get("agreeList");
        System.out.println("agree : " + agree);

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
        String decText = "ul-khGkBWJPku5AC7wT8bZwG4fyhGc5D_0NCXLOflnCelaq5GxtktElhc_bOW4RkCWON5ReCbynoC16DAu7bpw";
        System.out.println("decrypt : " + SecretAes.decrypt(decText));
    }
}
