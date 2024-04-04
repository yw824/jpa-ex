package com.leew.springjpa.repository;

import com.leew.springjpa.dto.User;
import org.springframework.cglib.core.Local;
import org.springframework.data.convert.Jsr310Converters;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface UserRespository extends JpaRepository<User, Long> {

    User findByName(String name); // 이름을 통해 User를 가져오는 메소드
    // 테스트 코드: void selectQueryMethod

    List<User> findAllByName(String name);

    User findByEmail(String email);

    User getByEmail(String email);

    User readByEmail(String email);

    User queryByEmail(String email);

    User searchByEmail(String email);

    User streamByEmail(String email);

    User findUserByEmail(String email);

    User findSomethingByEmail(String email);

    List<User> findFirst1ByName(String name);

    List<User> findTop1ByName(String name);

    List<User> findLast1ByName(String name);

    List<User> findByEmailAndName(String email, String name);

    List<User> findByEmailOrName(String email, String name);

    List<User> findByCreatedAtAfter(LocalDateTime yesterday);

    List<User> findByIdAfter(Long id);

    List<User> findByCreatedAtGreaterThan(LocalDateTime yesterday);

    List<User> findByCreatedAtGreaterThanEqual(LocalDateTime yesterday);

    List<User> findByCreatedAtBetween(LocalDate yesterday, LocalDate tomorrow);

    List<User> findByIdBetween(Long id1, Long id2);
}
