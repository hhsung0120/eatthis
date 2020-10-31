package co.kr.heeseong.eatthis.domain.user;

import co.kr.heeseong.eatthis.dto.User;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;

@Slf4j
@AllArgsConstructor
@Service
public class UserService {

    public User getUser(User user) throws Exception{

        return new User();
    }

    public Map<String, Object> getUserResult(User user){
        Map<String, Object> data = new LinkedHashMap<>();

        try{
            data.put("user", this.getUser(user));
        }catch (Exception e){
            log.info("getFaqListResult exception {}", e.getMessage());
        }
        return data;
    }
}
