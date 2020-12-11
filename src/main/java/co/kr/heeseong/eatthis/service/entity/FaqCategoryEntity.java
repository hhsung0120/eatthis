package co.kr.heeseong.eatthis.service.entity;


import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@Table(name = "faq_category")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FaqCategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Column
    private String categoryName;

    @Column
    private int order;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "faqCategoryEntity")
    private List<FaqEntity> faqEntityList = new ArrayList<>();
}
