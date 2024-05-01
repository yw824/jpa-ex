package com.leew.springjpa.dto;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@NoArgsConstructor
@Data
@ToString(callSuper=true)
@EqualsAndHashCode(callSuper=true)
public class BookAndAuthor extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @ToString.Exclude
    private Book book;

    @ManyToOne
    @ToString.Exclude
    private Author author;
}
