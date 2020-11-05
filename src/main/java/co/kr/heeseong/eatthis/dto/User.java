package co.kr.heeseong.eatthis.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.*;

@Builder
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class User {
    private Long idx;
    private String id;
    private String password;
    private String nickName;
    private String gender;
    private String birthday;
    private char foodAlarm;
    private char eventAlarm;
    private char serviceAlarm;
    private String profileImagePath;
}
