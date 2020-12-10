package co.kr.heeseong.eatthis.service.entity;


import co.kr.heeseong.eatthis.Enum.QuestionsStatus;
import co.kr.heeseong.eatthis.service.entity.common.TimeEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "questions")
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class QuestionsEntity extends TimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Column
    private Long userIdx;

    @ManyToOne
    @JoinColumn(name="category_idx")
    private FaqCategoryEntity faqCategoryEntity;

    @Column
    private String questions;

    @Column
    private String answer;

    @Enumerated(EnumType.STRING)
    private QuestionsStatus status;
}
