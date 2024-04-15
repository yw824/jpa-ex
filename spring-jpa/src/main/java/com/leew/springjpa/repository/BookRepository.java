package com.leew.springjpa.repository;

import com.leew.springjpa.dto.Book;
import com.leew.springjpa.dto.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
}
