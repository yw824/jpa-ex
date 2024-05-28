package com.leew.springjpa.service;

import com.leew.springjpa.repository.UserRespository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class userServiceTest {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRespository userRepository;

    @Test
    void transientTest() {
        userService.put();

        System.out.println("Transient >>> " + userRepository.findByEmail("newUser@fastcampus.com"));
        userRepository.findAll().forEach(System.out::println);
    }

}