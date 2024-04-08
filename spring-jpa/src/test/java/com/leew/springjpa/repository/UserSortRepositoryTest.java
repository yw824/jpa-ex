package com.leew.springjpa.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserSortRepositoryTest {

    @Autowired
    private UserSortRepository userRepository;

    @Test
    void top1Test() {
        /**
         * List<User> findTop1ByName(String name);
         * List<User> findTop1ByNameOrderByIdDesc(String name);
         * List<User> findFirst1ByNameOrderByIdDescEmailAsc(String name);
         */
        userRepository.findTop1ByName("martin").forEach(entity ->
                System.out.println(entity.getId())
        );
        userRepository.findTop1ByNameOrderByIdDesc("martin").forEach(entity ->
                System.out.println(entity.getId())
        );

        userRepository.findFirst1ByNameOrderByIdDescEmailAsc("martin")
                .forEach(System.out::println);
    }

    @Test
    void parameterSortTest() {
        /**
         *     List<User> findFirstByName(String name, Sort sort);
         */
        userRepository.findFirstByName("martin",
                     Sort.by(Sort.Order.desc("id"), Sort.Order.asc("email"))
                        )
                .forEach(System.out::println);
    }
}