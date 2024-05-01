package com.leew.springjpa.dto;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@NoArgsConstructor
@Data
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Author extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

//    @ManyToMany
//    @ToString.Exclude
//    private List<Book> books = new ArrayList<>(); // null pointer Exception 방지

    @OneToMany
    @JoinColumn(name="author_id")
    @ToString.Exclude
    private List<BookAndAuthor> bookAndAuthors = new ArrayList<>();

//    public void addBook(Book... book) {
//        Collections.addAll(this.books, book);
//    }

    public void addBookAndAuthors(BookAndAuthor... bookAndAuthor) {
        Collections.addAll(this.bookAndAuthors, bookAndAuthor);
    }
}
