package com.leew.springjpa.repository;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

@SpringBootTest
public class QueryMethodTest3 {

    @Autowired
    private UserRespository userRepository;

    @Test
    void notnullTest() {
        /**
         * List<User> findByIdIsNotNull();
         */
        userRepository.findByIdIsNotNull().forEach(System.out::println);
    }

    @Test
    void notemptyTest() {
        /**
         * List<User> findByAddressIsNotEmpty();
         */
        // userRepository.findByAddressIsNotEmpty().forEach(System.out::println);
    }

    @Test
    void in_notInTest() {
        /**
         * List<User> findByNameIn(List<String> names);
         */
        userRepository.findByNameIn(Lists.newArrayList("martin")).forEach(System.out::println);
    }

    @Test
    void sql_LikeTest() {
        /**
         *     List<User> findByNameStartingWith(String name);
         *     List<User> findByNameEndingWith(String name);
         *     List<User> findByNameContaining(String name);
         *     List<User> findByNameLike(String name);
         */
        userRepository.findByNameStartingWith("mart").forEach(System.out::println);
        userRepository.findByNameEndingWith("tin").forEach(System.out::println);
        userRepository.findByNameContaining("arti").forEach(System.out::println);

        System.out.println("_____________________________________________________");

        userRepository.findByNameLike("%art%").forEach(System.out::println);
    }
}
