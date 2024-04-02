package com.leew.springjpa.dto;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;


class UserTest {

    @Test
    void userTest() {
        User user = new User();
        user.setEmail("martin@fastcampus.com");
        user.setName("martin");

        System.out.println(">>> " + user);

        User user1 = new User(null, "user1", "user1@fastcampus.com", LocalDateTime.now(), LocalDateTime.now());
        User user2 = new User("user2", "user2@fastcampus.com");

        System.out.println(">>> " + user1);
        System.out.println(">>> " + user2);

    }
}