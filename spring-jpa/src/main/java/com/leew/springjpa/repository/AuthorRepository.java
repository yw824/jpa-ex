package com.leew.springjpa.repository;

import com.leew.springjpa.dto.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
