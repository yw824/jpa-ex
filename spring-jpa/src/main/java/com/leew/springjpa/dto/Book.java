package com.leew.springjpa.dto;

import com.leew.springjpa.listener.MyEntityListener;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Table(name="book")
@Data
@ToString(callSuper=true)
@EqualsAndHashCode(callSuper=true)
public class Book extends BaseEntity {
    /**
     * BaseEntity에서 AuditingEntityListener 설정하므로 여기서는 생략
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String author;

    private Long authorId;

    private Long publisherId;

    @OneToOne(mappedBy = "book")
    @ToString.Exclude
    private BookReviewInfo bookReviewInfo;
}
