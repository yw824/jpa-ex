package com.leew.springjpa.listener;

import com.leew.springjpa.dto.User;
import com.leew.springjpa.dto.UserHistory;
import com.leew.springjpa.repository.UserHistoryRepository;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PostUpdate;
import jakarta.persistence.PrePersist;
import com.leew.springjpa.support.BeanUtils;
import jakarta.persistence.PreUpdate;

public class UserEntityListener {

    @PostPersist
    @PostUpdate
    public void preUpdate(Object o) {
        UserHistoryRepository userHistoryRepository = BeanUtils.getBean(UserHistoryRepository.class);
        User user = (User) o;

        UserHistory userHistory = new UserHistory();
        // userHistory.setUserId(user.getId());
        userHistory.setName(user.getName());
        userHistory.setEmail(user.getEmail());
        userHistory.setUser(user);

        userHistoryRepository.save(userHistory);
    }
}
