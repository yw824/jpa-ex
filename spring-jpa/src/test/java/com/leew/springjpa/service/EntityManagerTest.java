package com.leew.springjpa.service;

import com.leew.springjpa.dto.User;
import com.leew.springjpa.repository.UserRespository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.FlushModeType;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Transactional
public class EntityManagerTest {
    @Autowired
    private EntityManager entityManager;
    @Autowired
    private UserRespository userRepository;

    @Test
    void test() {
        System.out.println(
                entityManager.createQuery( // JPQL 쿼리
                        "select u from User u"
                ).getResultList()
        );
    }

    @Test
    void cacheFindTest() {
//        System.out.println(userRepository.findById(1L).get());
//        System.out.println(userRepository.findById(1L).get());
//        System.out.println(userRepository.findById(1L).get());
//
//        System.out.println(userRepository.findByEmail("martin1@fastcampus.com"));
//        System.out.println(userRepository.findByEmail("martin1@fastcampus.com"));
//        System.out.println(userRepository.findByEmail("martin1@fastcampus.com"));

        userRepository.deleteById(1L);
    }

    @Test
    void cacheFindTest2() {
        User user = userRepository.findById(1L).get();
        System.out.println(user);
        user.setName("marrrrtin");

        userRepository.save(user);
        // userRepository.flush();

        System.out.println(userRepository.findById(1L).get());
        System.out.println("=====================\n");

        user.setEmail("marrrrrrrtin@fastcampus.com");
        userRepository.save(user);
        // userRepository.flush();

        System.out.println(userRepository.findById(1L).get());
        System.out.println("=====================\n");
        entityManager.persist(user);

        userRepository.findAll().forEach(System.out::println);
    }
}
