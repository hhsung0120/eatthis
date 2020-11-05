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

    public User getUser(Long idx) {
        try{
            Optional<UserEntity> optional = userRepository.findById(idx);
            if(optional.isPresent()){
                return User.builder()
                        .idx(optional.get().getIdx())
                        .id(optional.get().getId())
                        .nickName(optional.get().getNickName())
                        .password("")
                        .gender(optional.get().getUserDetailEntity().getGender().getValue())
                        .birthday(optional.get().getUserDetailEntity().getBirthday())
                        .foodAlarm(optional.get().getUserDetailEntity().getFoodAlarm())
                        .eventAlarm(optional.get().getUserDetailEntity().getEventAlarm())
                        .serviceAlarm(optional.get().getUserDetailEntity().getServiceAlarm())
                        .profileImagePath(optional.get().getUserDetailEntity().getProfileImagePath())
                        .build();
            }
            return new User();
        }catch (Exception e){
            log.info("getUserResult exception {}", e.getMessage());
            return new User();
        }
    }

    public Map<String, Object> getUserResult(Long idx){
        Map<String, Object> data = new LinkedHashMap<>();
        data.put("user", this.getUser(idx));
        return data;
    }
}
