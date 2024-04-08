package com.leew.springjpa.repository;

import com.leew.springjpa.dto.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserSortRepository extends JpaRepository<User, Long> {

    List<User> findTop1ByName(String name);

    List<User> findTop1ByNameOrderByIdDesc(String name);

    List<User> findFirst1ByNameOrderByIdDescEmailAsc(String name);

    // parameter 기반 QueryMethod
    List<User> findFirstByName(String name, Sort sort);
}
