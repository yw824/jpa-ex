package com.leew.springjpa.dto;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@Entity
@ToString(callSuper=true)
@EqualsAndHashCode(callSuper=true)
public class BookReviewInfo extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     *    book entity 그대로 참조, 그러나 그대로 참조하려면 1:1 연관관계 필요
     *    1:1 연관관계를 OneToOne 어노테이션으로 수행
     */
    @OneToOne(optional = false)
    private Book book;

    private float averageReviewScore; // 평점이므로 소수점 있을 것

    private int reviewCount; // 리뷰 남긴 사람의 수 : primitive Type
    /** small int를 사용한 이유 : null 허용 여부(기본 자료형은 불가능, 기본값을 0으로 설정)
     * primitive type에서는 table을 생성할 때 not-null로 생성
     * Wrapper Type은 null값 사용 가능
     */
}
