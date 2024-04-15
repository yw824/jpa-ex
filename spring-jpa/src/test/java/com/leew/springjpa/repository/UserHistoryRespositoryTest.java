package com.leew.springjpa.repository;

import com.leew.springjpa.dto.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserHistoryRespositoryTest {

    @Autowired
    private UserHistoryRepository userHistoryRepository;
    @Autowired
    private UserRespository userRepository;

    @Test
    void userHistoryTest() {
        User user = new User();
        user.setEmail("martin_history@fastcampus.com");
        user.setName("martin-history");

        userRepository.save(user);

        userHistoryRepository.findAll().forEach(System.out::println);
    }
}