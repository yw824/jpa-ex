package com.leew.springjpa.service;

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

    @Transactional
    @Test
    void transactionTest() {
        bookService.putBookAndAuthor();

        bookRepository.findAll().forEach(System.out::println);
        authorRepository.findAll().forEach(System.out::println);
    }

}