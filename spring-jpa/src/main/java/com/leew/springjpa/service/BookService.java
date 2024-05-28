package com.leew.springjpa.service;

import com.leew.springjpa.dto.Author;
import com.leew.springjpa.dto.Book;
import com.leew.springjpa.repository.AuthorRepository;
import com.leew.springjpa.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    @Transactional
    public void putBookAndAuthor() {
        Book book = new Book();
        book.setName("JPA 시작하기1111");

        bookRepository.save(book);

        Author author = new Author();
        author.setName("martin1111");

        authorRepository.save(author);

        throw new RuntimeException("오류가 나서 DB commit이 나지 않아야 합니다.");
    }
}
