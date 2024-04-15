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
        // user.setGender(Gender.MALE);

        userRepository.save(user);

        userRepository.findAll().forEach(System.out::println);
    }

    @Test
    void listenerTest() {
        User user = new User();
        user.setEmail("entityListener@fastcampus.com");
        user.setName("entityListener");

        userRepository.save(user);

        User user2 = userRepository.findById(2L).orElseThrow(RuntimeException::new);
        user2.setName("marrtin2");
        userRepository.save(user2);

        userRepository.deleteById(6L); // updatable+insertable 삭제 - mysql workbench 가서 매번 확인하자.
    }

    @Test
    void prePersistTest() {
        User user = new User();
        user.setEmail("martinprePersist@fastcampus.com");
        user.setName("martin");
        // user.setCreatedAt(LocalDateTime.now());
        // user.setUpdatedAt(LocalDateTime.now());

        userRepository.save(user);

        System.out.println(userRepository.findByEmail("martinprePersist@fastcampus.com"));
    }

    @Test
    void preUpdateTest() {
        User user = userRepository.findByEmail("martinprePersist@fastcampus.com");

        System.out.println("as-is >>> " + user);
        user.setEmail("martin_prePersist_preUpdate@fastcampus.com");

        userRepository.save(user);
        System.out.println("to-be >>>" +
            userRepository.findAll().get(6) // 0번째 인덱스부터 6번째 index의 데이터 가져와서 출력
        );
    }
}
