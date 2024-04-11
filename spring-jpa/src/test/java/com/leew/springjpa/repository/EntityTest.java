package com.leew.springjpa.repository;

import com.leew.springjpa.dto.Gender;
import com.leew.springjpa.dto.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
public class EntityTest {

    @Autowired
    private UserRespository userRepository;

    @Test
    void insertable_and_updatable_Test() {
        User user = new User();
        user.setName("insertable");
        user.setEmail("insertable@fastcampus.com");
        user.setCreatedAt(LocalDateTime.now());

        System.out.println(user);
        userRepository.save(user);

        User user2 = userRepository.findByName("insertable");
        user2.setName("insertable+updatable");
        user2.setTestData("test-data");
        user2.setUpdatedAt(LocalDateTime.now());

        userRepository.save(user2);
    }

    @Test
    void enumTest() {
        User user = userRepository.findById(1L).orElseThrow(RuntimeException::new);
        user.setGender(Gender.MALE);

        userRepository.save(user);

        userRepository.findAll().forEach(System.out::println);
    }
}
