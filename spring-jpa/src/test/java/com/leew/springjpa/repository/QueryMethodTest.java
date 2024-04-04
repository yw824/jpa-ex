package com.leew.springjpa.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class QueryMethodTest {

    @Autowired
    private UserRespository userRepository;

    @Test
    void select() {
        System.out.println(userRepository.findByName("update"));

        System.out.println("findByEmail: " + userRepository.findByEmail("updateUser@fastcampus.com"));
        System.out.println("getByEmail: " + userRepository.getByEmail("updateUser@fastcampus.com"));
        System.out.println("readByEmail: " + userRepository.readByEmail("updateUser@fastcampus.com"));
        System.out.println("queryByEmail: " + userRepository.queryByEmail("updateUser@fastcampus.com"));
        System.out.println("streamByEmail: " + userRepository.streamByEmail("updateUser@fastcampus.com"));
        System.out.println("findUserByEmail: " + userRepository.findUserByEmail("updateUser@fastcampus.com"));
        // 모두 같은 query, 같은 결과물을 출력
    }

    @Test
    void somethingTest() {
        System.out.println("findSomethingByEmail: " + userRepository.findSomethingByEmail("updateUser@fastcampus.com"));
    }

    @Test
    void topFirstTest() {
        System.out.println("top: " + userRepository.findTop1ByName("martin"));
        System.out.println("first: " + userRepository.findFirst1ByName("martin"));
    }

    @Test
    void lastTest() {
        userRepository.findLast1ByName("martin").forEach(System.out::println);
        // last는 작동하지 않는다. QueryMethod에서는 작동하지 않는 키워드
    }
}
