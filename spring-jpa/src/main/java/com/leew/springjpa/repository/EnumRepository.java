package com.leew.springjpa.repository;

import com.leew.springjpa.dto.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Map;
import java.util.Optional;

public interface EnumRepository extends JpaRepository<User, Long> {

    Optional<User> findById(Long id);

    @Query(value="select * from user limit 1;", nativeQuery = true)
    Map<String, Object> findRowRecord();
}
