package com.leew.springjpa.dto;

import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@Data
@AllArgsConstructor
@ToString(callSuper=true)
@EqualsAndHashCode(callSuper = true)
public class Review extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // pk 값

    private String title;

    private String content;

    private float score;

    // user_id는 ManyToOne, book_id는 ManyToOne
    @ManyToOne
    private User user;

    @ManyToOne
    private Book book;

    // createdAt, updatedAt은 BaseEntity에서 가지고 있음

}
