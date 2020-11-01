package co.kr.heeseong.eatthis.domain.user;

import co.kr.heeseong.eatthis.dto.User;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

@Slf4j
@AllArgsConstructor
@Service
public class UserService {

    final private UserRepository userRepository;

    public User getUser(Long idx) throws Exception{

        Optional<UserEntity> optional = userRepository.findById(idx);
        if(!optional.isPresent()){
            return User.builder()
                    .id(optional.get().getId())
                    .nickName(optional.get().getNickName())
                    .profileImagePath(optional.get().getUserDetailEntity().getProfileImagePath())
                    .build();
        }
        return null;
    }

    public Map<String, Object> getUserResult(Long idx){
        Map<String, Object> data = new LinkedHashMap<>();

        try{
            data.put("user", this.getUser(idx));
        }catch (Exception e){
            log.info("getUserResult exception {}", e.getMessage());
        }
        return data;
    }
}
