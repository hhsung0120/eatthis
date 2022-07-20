package co.kr.eatthis.faq.domain.entity;


import co.kr.eatthis.common.domain.entity.TimeAndUserIdEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Entity
@ToString
@Table(name = "faq")
public class FaqEntity extends TimeAndUserIdEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;

    @Column
    private Long categorySeq;

    @Column
    private String title;

    @Column
    private String contents;

    public FaqEntity() {
    }
}
