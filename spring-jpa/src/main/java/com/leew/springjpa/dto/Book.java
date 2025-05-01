package com.leew.springjpa.dto;

import com.leew.springjpa.listener.MyEntityListener;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@NoArgsConstructor
@Table(name="book")
@Data
@ToString(callSuper=true)
@EqualsAndHashCode(callSuper=true)
@DynamicUpdate
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

    private String category;

    // Book : BookReviewInfo = 1 : 1 -> 수평적 확장
    @OneToOne(mappedBy = "book")
    @ToString.Exclude
    private BookReviewInfo bookReviewInfo;

    // Book : Review = 1 : N
    @OneToMany
    @JoinColumn(name="book_id")
    @ToString.Exclude
    private List<Review> reviews = new ArrayList<>();

    // Book : Publisher = N : 1
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn
    @ToString.Exclude
    private Publisher publisher;

//    @ManyToMany
//    @ToString.Exclude
//    private List<Author> authors = new ArrayList<>();

    @OneToMany
    @JoinColumn(name="book_id")
    @ToString.Exclude
    private List<BookAndAuthor> bookAndAuthors = new ArrayList<>();

//        public void addAuthor(Author... author) {
//        Collections.addAll(this.authors, author);
//    }
    public void addBookAndAuthors(BookAndAuthor... bookAndAuthor) {
        Collections.addAll(this.bookAndAuthors, bookAndAuthor);
    }
}
