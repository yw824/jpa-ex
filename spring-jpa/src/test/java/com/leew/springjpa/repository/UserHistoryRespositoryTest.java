package com.leew.springjpa.repository;

import com.leew.springjpa.dto.Book;
import com.leew.springjpa.dto.Gender;
import com.leew.springjpa.dto.User;
import com.leew.springjpa.dto.UserHistory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

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

    /**
     * User Relation 관련 테스트
     */
    @Test
    void userRelationTest() {
        User user = new User();
        user.setName("David");
        user.setEmail("david@fastcampus.com");
        user.setGender(Gender.MALE);

        userRepository.save(user);

        // 2번의 update
        user.setName("daniel");
        userRepository.save(user);
        user.setEmail("daniel@fastcampus.com");
        userRepository.save(user);

        userRepository.findAll().forEach(System.out::println);
        userHistoryRepository.findByUserId(
            userRepository.findByEmail("daniel@fastcampus.com").getId()
        ).forEach(System.out::println);
    }

    // db-keep.yml로 만들었을 때 데이터 생성 없는 코드만 추출
    @Test
    void relationTempTest() {
        userHistoryRepository.findByUserId(
                userRepository.findByEmail("daniel@fastcampus.com").getId()
        ).forEach(System.out::println);
    }

    @Test // db-keep.yml 설정으로 변환해 놓고 수행하기
    void relationMappedTest() {
        User user = new User();
        user.setName("David");
        user.setEmail("david@fastcampus.com");
        user.setGender(Gender.MALE);

        userRepository.save(user);

        // 2번의 update
        user.setName("daniel");
        userRepository.save(user);
        user.setEmail("daniel@fastcampus.com");
        userRepository.save(user);

//        userRepository.findAll().forEach(System.out::println);
//        userHistoryRepository.findByUserId(
//                userRepository.findByEmail("daniel@fastcampus.com").getId()
//        ).forEach(System.out::println);

        List<UserHistory> result = userRepository.findByEmail("daniel@fastcampus.com").getUserHistories();
        result.forEach(System.out::println);

        userHistoryRepository.findAll().forEach(System.out::println);

        System.out.println("UserHistory.getUser(): " + userHistoryRepository.findAll().get(0).getUser());
    }
}