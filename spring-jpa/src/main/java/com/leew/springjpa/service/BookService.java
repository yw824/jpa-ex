package com.leew.springjpa.service;

import com.leew.springjpa.dto.Author;
import com.leew.springjpa.dto.Book;
import com.leew.springjpa.repository.AuthorRepository;
import com.leew.springjpa.repository.BookRepository;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final EntityManager entityManager;

    @Transactional(rollbackFor = Exception.class)
    public void putBookAndAuthor() {
        Book book = new Book();
        book.setName("JPA 시작하기_forRollback333");

        bookRepository.save(book);

        Author author = new Author();
        author.setName("martinforRollBack333");

        authorRepository.save(author);

        throw new RuntimeException("오류가 나서 DB commit이 나지 않아야 합니다.");
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void get(String name) {
        System.out.println(">>> " + bookRepository.findByName(name)); // breakpoint 1
        bookRepository.findAll().forEach(System.out::println);

        System.out.println(">>> " + bookRepository.findByName(name)); // breakpoint 2
        bookRepository.findAll().forEach(System.out::println);

        Book book = bookRepository.findByName(name);
        book.setName("바뀔까???");
        bookRepository.save(book);
        // breakpoint 3
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public void get(Long id) {

        System.out.println("001 >>> " + bookRepository.findById(id));
        System.out.println("002 >>> " + bookRepository.findAll());

        // EntityManager로 EntityCache 삭제
        entityManager.clear();

        System.out.println("003 >>> " + bookRepository.findById(id));
        System.out.println("004 >>> " + bookRepository.findAll());

    }
}
