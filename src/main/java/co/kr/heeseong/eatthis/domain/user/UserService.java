package co.kr.heeseong.eatthis.domain.user;

import co.kr.heeseong.eatthis.Enum.GenderType;
import co.kr.heeseong.eatthis.domain.test.TestEntity;
import co.kr.heeseong.eatthis.dto.User;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Slf4j
@AllArgsConstructor
@Service
public class UserService {

    final private UserRepository userRepository;
    final private UserDetailRepository userDetailRepository;

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
    @Transactional
    public long saveUser(User user){
        if(user.getIdx() == 0){
            return this.insertUser(user);
        }else{
            return this.updateUser(user);
        }
    }

    public long insertUser(User user){
        long idx = 0;
        try{
            idx = userRepository.save(user.toEntity()).getIdx();
            if(idx > 0){
                userDetailRepository.save(user.toDetailEntity(idx));
            }
        }catch (DataIntegrityViolationException e){
            throw new DataIntegrityViolationException("중복 된 아이디 입니다. -> " + user.getId());
        }

        return idx;
    }

    public long updateUser(User user) throws IllegalArgumentException{
        UserDetailEntity userDetailEntity = userDetailRepository.findById(user.getIdx()).orElseThrow(() -> new IllegalArgumentException("유저가 존재하지 않습니다. -> " + user.getIdx()));
        userDetailEntity.update(user.getProfileImagePath(), user.getNickName(), user.getBirthday(), GenderType.getGenderTypeToEnum(user.getGender()));
        return userDetailEntity.getIdx();
    }
}
