package co.kr.heeseong.eatthis;

import co.kr.heeseong.eatthis.common.util.SecretAes;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SecretAesTests {

    @Test
    public void signUp() throws Exception {
        String encText = SecretAes.encrypt("test");
        System.out.println("encText : " + encText);
        System.out.println(SecretAes.decryptWithoutCatch("A_N_-CJY-apgLrV5P5SgOW_z5DUawY1eP-XDgouSfabNfZqJ2KqH6heQ6Av6F5UiPF1fx-ANw_nz04OXDW0BXHGrc-jCG2yY-buBANJP5B8NahgrstLoJTTfD1zFq3XAf5IgvcLZe0BQoC9AgOrp4A"));
    }
}
