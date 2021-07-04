package co.kr.heeseong.eatthis.entity.common;


import co.kr.heeseong.eatthis.Enum.TableCode;
import lombok.*;

import javax.persistence.*;

@Builder
@Entity
@Table(name = "file")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
public class FileEntity extends TimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idx;

    @Enumerated(EnumType.STRING)
    private TableCode tableType;

    @Column
    private long tableIdx;

    @Column
    private String uuid;

    @Column
    private String originalFileName;

    @Column
    private String extension;
}
