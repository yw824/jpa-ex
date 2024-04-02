package com.leew.springjpa.repository;

import com.leew.springjpa.dto.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRespository extends JpaRepository<User, Long> {
}
