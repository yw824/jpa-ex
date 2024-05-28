package com.leew.springjpa.service;

import com.leew.springjpa.dto.User;
import com.leew.springjpa.repository.UserRespository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private UserRespository userRepository;

    @Transactional
    public void put() {
        User user = new User();
        user.setName("newUser");
        user.setEmail("newUser@fastcampus.com");

        entityManager.persist(user);
        // entityManager.detach(user);

        user.setName("newUserAfterPersist");
        entityManager.merge(user);

        User user1 = userRepository.findById(1L).get();
        entityManager.remove(user1);
    }
}
