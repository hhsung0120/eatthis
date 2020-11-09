package co.kr.heeseong.eatthis.domain.user;

import ch.qos.logback.core.encoder.EchoEncoder;
import co.kr.heeseong.eatthis.dto.User;
import co.kr.heeseong.eatthis.util.StringUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Optional;

@Slf4j
@AllArgsConstructor
@Service
public class UserService {

    final private UserRepository userRepository;

    /**
     * 유저 정보
     * @param idx
     * @return
     */
    public User getUser(Long idx) {
        try{
            Optional<UserEntity> optional = userRepository.findById(idx);
            if(optional.isPresent()){
                return User.builder()
                        .idx(optional.get().getIdx())
                        .id(optional.get().getId())
                        .nickName(optional.get().getUserDetailEntity().getNickName())
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

    /**
     * 회원가입
     * @param user
     */

    public long saveUser(User user){
        if(user.getIdx() == 0){
            return this.insertUser(user);
        }else{
            return this.updateUser(user);
        }
    }

    public long insertUser(User user){
        return userRepository.save(user.toEntity()).getIdx();
    }

    @Transactional
    public long updateUser(User user) {
        return 0;
    }

}
