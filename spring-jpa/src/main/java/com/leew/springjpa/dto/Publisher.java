package com.leew.springjpa.dto;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Publisher extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    // OneToMany : Book과 OneToMany의 관계
    @OneToMany
    @JoinColumn(name = "publisher_id") // Book과 Publisher의 중간 테이블을 없애기 위함
    @ToString.Exclude
    // 위 JoinColumn 없이 중간 테이블을 미리 만들고 나서 생성되는 fk 변수명으로 매핑할 것
    private List<Book> books = new ArrayList<>(); // NullPointerException 방지

    public void addBook(Book book) {
        this.books.add(book);
    }
}
