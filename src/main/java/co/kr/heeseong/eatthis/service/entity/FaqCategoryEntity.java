package co.kr.heeseong.eatthis.service.entity;


import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
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

    @OneToMany(mappedBy = "faqCategoryEntity")
    private Set<FaqEntity> faqEntityList = new HashSet<>();
}
