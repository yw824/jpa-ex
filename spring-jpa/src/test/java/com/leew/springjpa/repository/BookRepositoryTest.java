package com.leew.springjpa.repository;

import com.leew.springjpa.dto.Publisher;
import com.leew.springjpa.dto.Review;
import com.leew.springjpa.dto.User;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.leew.springjpa.dto.Book;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Test
    void prePersistTest() {
        Book book = new Book();
        book.setName("JPA 초격차 패키지");
        book.setAuthor("패스트캠퍼스");
        // book.setPublisherId(1L);

        bookRepository.save(book);

        System.out.println(bookRepository.findAll());
    }

    // 사실 이 클래스에서는 BookRepository에 대한 테스트만 해야 하지만
    // 아래 relation 관련 테스트만 해야 하기에
    // 필요한 모든 repository를 생성할 예정
    @Autowired
    private PublisherRepository publisherRepository;
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private UserRespository userRepository;

    @Test
    @Transactional
    void bookRelationTest() {
        givenBookAndReview(); // test할 정보 저장

        User user;
        user = userRepository.findByEmail("martin1@fastcampus.com");

        System.out.println(">>> Review : " + user.getReviews()); // User가 작성한 review 모두 보기
        System.out.println(">>> Book : " + user.getReviews().get(0).getBook());
        System.out.println(">>> Publisher : " + user.getReviews().get(0).getBook().getPublisher());
    }

    private void givenBookAndReview() {
        givenReview(givenUser(), givenBook(givenPublisher()));
    }

    private User givenUser() {
        return userRepository.findByEmail("martin1@fastcampus.com"); // data.sql의 1번 user가 저장될 것
    }

    private Book givenBook(Publisher publisher) {
        Book book = new Book();
        book.setName("JPA 초격차 패키지");
        book.setPublisher(publisher); // publisher를 받아서 넣어 주자.

        return bookRepository.save(book);
    }

    private Publisher givenPublisher() {
        Publisher publisher = new Publisher();
        publisher.setName("패스트캠퍼스");

        return publisherRepository.save(publisher);
    }

    private void givenReview(User user, Book book) {
        Review review = new Review();
        review.setTitle("내 인생을 바꾼 책");
        review.setContent("너무너무 재미있고 즐거운 책이였어요.");
        review.setBook(book);
        review.setUser(user);
        review.setScore(5.0f);

        reviewRepository.save(review);
    }
}