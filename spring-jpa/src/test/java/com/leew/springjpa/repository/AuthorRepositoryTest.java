package com.leew.springjpa.repository;

import com.leew.springjpa.dto.Author;
import com.leew.springjpa.dto.Book;
import com.leew.springjpa.dto.BookAndAuthor;
import jakarta.transaction.Transactional;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AuthorRepositoryTest {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookRepository bookRepository;

    // ManyToMany 테스트는 많이 복잡하다.
    @Test
    @Transactional
    void manyToManyTest1() {
        // 책도 여러 권 저장하고 저자도 여러 명 저장해야 한다. 함수로 재사용
        Book book1 = givenBook("책1");
        Book book2 = givenBook("책2");
        Book book3 = givenBook("개발책1");
        Book book4 = givenBook("개발책2");

        Author author1 = givenAuthor("martin");
        Author author2 = givenAuthor("steve");

//        book1.addAuthor(author1);
//        book2.addAuthor(author2);
//        book3.addAuthor(author1, author2);
//        book4.addAuthor(author1, author2);
//
//        // author는 아무렇게나 넣으면 안되고, 위의 book1~4를 맞게 매핑해야 한다.
//        // author1.setBooks(Lists.newArrayList(book1, book3, book4));
//        author1.addBook(book1, book3, book4);
//        author2.addBook(book2, book3, book4);
//
//        bookRepository.saveAll(Lists.newArrayList(book1, book2, book3, book4));
//        authorRepository.saveAll(Lists.newArrayList(author1, author2));
//
//        // 공동 저자의 책을 가져온다.
//        System.out.println("authors through book : " + bookRepository.findAll().get(2).getAuthors());
//        // 여러 권을 작성한 author1을 가져온다,
//        System.out.println("books through author : " + authorRepository.findAll().get(0).getBooks());
    }

    private Book givenBook(String name) {
        Book book = new Book();
        book.setName(name);

        return bookRepository.save(book);
    }

    private Author givenAuthor(String name) {
        Author author = new Author();
        author.setName(name);

        return authorRepository.save(author);
    }

    @Autowired
    private BookAndAuthorRepository bookAndAuthorRepository;

    @Test
    @Transactional
    void manyToManyTest2() {
        // 책도 여러 권 저장하고 저자도 여러 명 저장해야 한다. 함수로 재사용
        Book book1 = givenBook("책1");
        Book book2 = givenBook("책2");
        Book book3 = givenBook("개발책1");
        Book book4 = givenBook("개발책2");

        Author author1 = givenAuthor("martin");
        Author author2 = givenAuthor("steve");

//        book1.addAuthor(author1);
//        book2.addAuthor(author2);
//        book3.addAuthor(author1, author2);
//        book4.addAuthor(author1, author2);
//        author1.addBook(book1, book3, book4);
//        author2.addBook(book2, book3, book4);
        // bookRepository.saveAll(Lists.newArrayList(book1, book2, book3, book4));
        // authorRepository.saveAll(Lists.newArrayList(author1, author2));

        BookAndAuthor bookAndAuthor1 = givenBookAndAuthor(book1, author1);
        BookAndAuthor bookAndAuthor2 = givenBookAndAuthor(book2, author2);
        BookAndAuthor bookAndAuthor3 = givenBookAndAuthor(book3, author1);
        BookAndAuthor bookAndAuthor4 = givenBookAndAuthor(book3, author2);
        BookAndAuthor bookAndAuthor5 = givenBookAndAuthor(book4, author1);
        BookAndAuthor bookAndAuthor6 = givenBookAndAuthor(book4, author2);
        // saveAll 안한다. 이미 givenBookAndAuthor 함수에 repository로 저장하는 로직이 있기 때문
        // BookAndAuthor를 작성
        book1.addBookAndAuthors(bookAndAuthor1);
        book2.addBookAndAuthors(bookAndAuthor2);
        book3.addBookAndAuthors(bookAndAuthor3, bookAndAuthor4);
        book4.addBookAndAuthors(bookAndAuthor5, bookAndAuthor6);

        author1.addBookAndAuthors(bookAndAuthor1, bookAndAuthor3, bookAndAuthor5);
        author2.addBookAndAuthors(bookAndAuthor2, bookAndAuthor4, bookAndAuthor6);

        // 책의 공동 저자들을 가져온다.
        // System.out.println("authors through book : " + bookRepository.findAll().get(2).getAuthors());
        bookRepository.findAll().get(2).getBookAndAuthors().forEach(o -> System.out.println(o.getAuthor()));

        // 여러 권을 작성한 author1의 집필한 책을 가져온다,
        // System.out.println("books through author : " + authorRepository.findAll().get(0).getBooks());
        authorRepository.findAll().get(0).getBookAndAuthors().forEach(o -> System.out.println(o.getBook()));
    }

    private BookAndAuthor givenBookAndAuthor(Book book, Author author) {
        BookAndAuthor bookAndAuthor = new BookAndAuthor();
        bookAndAuthor.setAuthor(author);
        bookAndAuthor.setBook(book);

        return bookAndAuthorRepository.save(bookAndAuthor);
    }
}