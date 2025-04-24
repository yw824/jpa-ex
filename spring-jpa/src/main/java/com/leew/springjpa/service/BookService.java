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
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BookService {

    // Bean 주입받기
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final EntityManager entityManager;
    private final AuthorService authorService;

    @Transactional(propagation = Propagation.REQUIRED)
    public void putBookAndAuthor() {
        Book book = new Book();
        book.setName("JPA 시작하기_forRollback333");

        bookRepository.save(book);

        // transaction 확인 위해 authorService 새로 생성
        try {
            authorService.putAuthor();
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }

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

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void get(Long id) {

        System.out.println("001 >>> " + bookRepository.findById(id));
        System.out.println("002 >>> " + bookRepository.findAll());

        // EntityManager로 EntityCache 삭제
        entityManager.clear();

        System.out.println("003 >>> " + bookRepository.findById(id));
        System.out.println("004 >>> " + bookRepository.findAll());

        bookRepository.update();
        entityManager.clear();
    }
}
