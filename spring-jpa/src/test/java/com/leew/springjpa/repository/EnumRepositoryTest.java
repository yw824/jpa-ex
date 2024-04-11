package com.leew.springjpa.repository;

import com.leew.springjpa.dto.Gender;
import com.leew.springjpa.dto.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EnumRepositoryTest {

    @Autowired
    private EnumRepository userRepository;

    @Test
    void enumTest() {
        User user = userRepository.findById(1L).orElseThrow(RuntimeException::new);
        user.setGender(Gender.MALE);

        userRepository.save(user);

        userRepository.findAll().forEach(System.out::println);
    }

    @Test
    void native_enumTest() {
        System.out.println(
                userRepository.findRowRecord().get("gender")
        );
    }
}