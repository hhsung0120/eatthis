package co.kr.heeseong.eatthis.service.entity;


import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Table(name = "user_secession")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@ToString
@Builder
public class UserSecessionEntity{

    @Id
    private long userIdx;

    @Column
    private long secessionReasonIdx;

    @Column
    private String memo;
}
