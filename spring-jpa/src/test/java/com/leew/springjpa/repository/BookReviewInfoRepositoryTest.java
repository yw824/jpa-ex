package com.leew.springjpa.repository;

import com.leew.springjpa.dto.Book;
import com.leew.springjpa.dto.BookReviewInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.SQLOutput;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BookReviewInfoRepositoryTest {

    @Autowired
    private BookReviewInfoRepository bookReviewInfoRepository;
    @Autowired
    private BookRepository bookRepository;

    @Test
    void crudTest() {
        BookReviewInfo bookReviewInfo = new BookReviewInfo();
        // bookReviewInfo.setBookId(1L);
        bookReviewInfo.setAverageReviewScore(4.5f);
        bookReviewInfo.setReviewCount(2);

        bookReviewInfoRepository.save(bookReviewInfo);

        bookReviewInfoRepository.findAll().forEach(System.out::println);
    }

    @Test
    void crudTest2() {
        Book book = new Book();
        book.setName("JPA 초격차 crudTest2");
        book.setAuthorId(1L);
        book.setPublisherId(1L);

        bookRepository.save(book);

        BookReviewInfo bookReviewInfo = new BookReviewInfo();
        // bookReviewInfo.setBookId(1L);
        bookReviewInfo.setAverageReviewScore(4.5f);
        bookReviewInfo.setReviewCount(2);

        bookReviewInfoRepository.save(bookReviewInfo);

        bookReviewInfoRepository.findAll().forEach(System.out::println);

//        Book result = bookRepository.findById(
//                // findById : Optional 자료형
//                bookReviewInfoRepository.findById(1L).orElseThrow(RuntimeException::new).getBookId()
//        ).orElseThrow(RuntimeException::new);
//        // 밖에서도 orElseThrow 안하면 에러 처리
//
//        System.out.println("1번 bookId 가진 객체 >>> : " + result);
    }

    private Book givenBook() {
        Book book = new Book();
        book.setName("JPA 초격차 패키지");
        book.setAuthorId(1L);
        book.setPublisherId(1L);

        // save 함수는, 저장된 entity를 그대로 리턴하도록 되어 있다.
        return bookRepository.save(book);
    }

    @Test
    void givenBookReviewInfo() {
        // BookReviewInfo에서, bookId가 아니라 Entity인 Book을 그대로 담은 후에 테스트 수행
        BookReviewInfo bookReviewInfo = new BookReviewInfo();
        // bookReviewInfo.setBookId(1L);
        bookReviewInfo.setBook(givenBook());
        bookReviewInfo.setAverageReviewScore(4.5f);
        bookReviewInfo.setReviewCount(2);

        bookReviewInfoRepository.save(bookReviewInfo);

        bookReviewInfoRepository.findAll().forEach(System.out::println);
    }

    @Test
    void crudTest3() {
        givenBookReviewInfo();

        Book result = bookReviewInfoRepository.findById(1L).orElseThrow(RuntimeException::new).getBook();
        System.out.println("Book from BookReviewInfo >>>" + result);

        BookReviewInfo result2 = bookRepository.findById(1L)
                .orElseThrow(RuntimeException::new)
                .getBookReviewInfo();

        System.out.println("BookReviewInfo from Book >>>" + result2);
    }
}