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

    @Test
    // @Transactional
    void bookCascadeTest() {
        Book book = new Book();
        book.setName("JPA 초격차 패키지 - cascade");

        // bookRepository.save(book); // 영속화

        /**
        book 엔티티는 많은 다른 엔티티와 릴레이션 맺고 있지만,
        간단한 관계 위해 publisher를 설정할 예정
        */
        Publisher publisher = new Publisher();
        publisher.setName("패스트캠퍼스 - cascade");

        // publisherRepository.save(publisher);
        book.setPublisher(publisher);

        // bookRepository.save(book);

        // publisher의 book은 리스트 형식으로 저장되어 있으므로
        // 먼저 가져온 후에 add하여 다시 저장
        // publisher.getBooks().add(book); // call by reference
        // books ArrayList를 가져온 다음에 add해도 해당 publisher에 저장됨

        // add하는 함수를 아예 publisher Entity에 추가
        publisher.addBook(book);

        // cascade : 맨 마지막에만 추가한다.
        bookRepository.save(book);
        publisherRepository.save(publisher);

        System.out.println("books: " + bookRepository.findAll());
        System.out.println("publishers: " + publisherRepository.findAll());

        Book book1 = bookRepository.findById(104L).get();
        book1.getPublisher().setName("슬로우캠퍼스");

        bookRepository.save(book1);

        System.out.println("publishers: >>> " );
        publisherRepository.findAll().forEach(System.out::println);
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