package com.leew.springjpa.repository;

import com.leew.springjpa.dto.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PublisherRepository extends JpaRepository<Publisher, Long> {
}
