package co.kr.heeseong.eatthis.model;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class FaqCategory {
    private long idx;
    private String categoryName;
}
