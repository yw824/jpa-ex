package com.leew.springjpa.repository;

import com.leew.springjpa.dto.UserHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserHistoryRepository extends JpaRepository<UserHistory, Long> {

}
