package com.leew.springjpa.repository;

import com.leew.springjpa.dto.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
