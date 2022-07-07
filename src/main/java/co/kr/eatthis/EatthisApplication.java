package co.kr.eatthis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class EatthisApplication {

    public static void main(String[] args) {
        SpringApplication.run(EatthisApplication.class, args);
    }

}
