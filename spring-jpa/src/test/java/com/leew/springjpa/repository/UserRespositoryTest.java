package com.leew.springjpa.repository;

import com.leew.springjpa.dto.User;
import jakarta.transaction.Transactional;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserRespositoryTest {

    @Autowired
    private UserRespository userRepository;

    @Test
    void crud() {
        userRepository.save(new User());

        userRepository.findAll().forEach(System.out::println);
    }

    @Test
    @Transactional
    void getOne() {
        Optional<User> result = null;
        result = userRepository.findById(1L);
        System.out.println(result.get());
        System.out.println(result.get().getClass().getName());
    }

    @Test
    void save() {
        userRepository.save(new User("new Martin", "newMartin@fastcampus.com"));

        userRepository.flush();
        userRepository.findAll().forEach(System.out::println);
    }

    @Test
    void count() {
        boolean exists = userRepository.existsById(1L);
        // exists를 위해 count query를 사용한다. (로그를 살펴보면 알 수 있음)
        long count = userRepository.count();
        System.out.println("1L exists: " + exists + ", counts: " + count);
    }

    @Test
    void delete() {
        // userRepository.delete(userRepository.findById(152L).orElse(null));

        // userRepository.deleteAll(userRepository.findAllById(Lists.newArrayList(1L, 3L)));

        userRepository.deleteInBatch(userRepository.findAllById(Lists.newArrayList(252L, 202L)));

        /*
        userRepository.delete(userRepository.findById(152L).orElseThrow(() -> new RuntimeException()));
         */
    }

    @Test
    void Paging() {
        Page<User> users = userRepository.findAll(PageRequest.of(1, 3));

        System.out.println("page: " + users); // Page 2 of 2 containing com.leew.springjpa.dto.User instances
        System.out.println("totalElements: " + users.getTotalElements());
        System.out.println("totalPages: " + users.getTotalPages());
        System.out.println("numberOfElements: " + users.getNumberOfElements());
        System.out.println("sort: " + users.getSort());
        System.out.println("size: " + users.getSize()); // 페이징 사이즈

        users.getContent().forEach(System.out::println);
    }

    @Test
    void example() {
        // Matcher : 매치할 컬럼과 매치하지 않을 컬럼을 표시
        // 매치할 컬럼은, 시작 / 중간 / 마지막 등 조건을 설정 가능
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnorePaths("name") // 확인 안하겠다
                .withMatcher("email", ExampleMatcher.GenericPropertyMatchers.endsWith());

        // 해당 Matcher에 각각 매칭될 패턴을 달아주는 역할
        // probe : 가짜 Entity -> 마치 Entity의 형태처럼 제시하여, 해당 객체의 멤버변수를 Matcher의 패턴으로 적용
        // 여기서 이름도 설정해 놓았으나, 위의 Matcher가 name을 ignore하였으므로, 작동하지 않는다.
        Example<User> example = Example.of(new User("mo", "fastcampus.com"), matcher);

        userRepository.findAll(example).forEach(System.out::println);
    }

    @Test
    void example2() {
        User user = new User();
        user.setEmail("fast");

        ExampleMatcher matcher = ExampleMatcher.matching().withMatcher("email",
                ExampleMatcher.GenericPropertyMatchers.contains());

        Example<User> example = Example.of(user, matcher);
        userRepository.findAll(example).forEach(System.out::println);
    }

    @Test
    void update() {
        userRepository.save(new User("update", "updateUser@fastcampus.com"));

        User user = userRepository.findById(1L).orElseThrow(RuntimeException::new);
        user.setEmail("martin-updated@fastcampus.com");

        userRepository.save(user);
    }

    @Test
    void selectQueryMethod() {
        // martin이라는 이름이 현재 DB에는 여러 개일 것이기 떄문에 에러 발생할 것
        // System.out.println(userRepository.findByName("martin"));

        // 이러면, 여러 개의 값을 가져오기 위해 List<User>를 위한 함수를 가져와야 한다.
        // UserRepository 코드 : List<User> findAllByName(String name);
        userRepository.findAllByName("martin").forEach(System.out::println);
    }
}