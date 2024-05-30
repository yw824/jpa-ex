package com.leew.springjpa.service;

import com.leew.springjpa.dto.Book;
import com.leew.springjpa.repository.AuthorRepository;
import com.leew.springjpa.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BookServiceTest {
    @Autowired
    private BookService bookService;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private AuthorRepository authorRepository;

    @Test
    void transactionTest() {
        try {
            bookService.putBookAndAuthor();
        } catch (Exception e) {
            System.out.println(">>> " + e.getMessage());
        }
        bookRepository.findAll().forEach(System.out::println);
        authorRepository.findAll().forEach(System.out::println);
    }

    @Test
    void isolationTest() {
        Book book = new Book();
        book.setName("JPA Isolation Test");
        bookRepository.save(book);

        bookService.get("JPA Isolation Test");
        System.out.println("Isolation Test: ");
        bookRepository.findAll().forEach(System.out::println); // breakpoint 4
    }

}