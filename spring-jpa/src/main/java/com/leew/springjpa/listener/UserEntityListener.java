package com.leew.springjpa.listener;

import com.leew.springjpa.dto.User;
import com.leew.springjpa.dto.UserHistory;
import com.leew.springjpa.repository.UserHistoryRepository;
import jakarta.persistence.PrePersist;
import com.leew.springjpa.support.BeanUtils;
import jakarta.persistence.PreUpdate;

public class UserEntityListener {

    @PreUpdate
    @PrePersist
    public void preUpdate(Object o) {
        UserHistoryRepository userHistoryRepository = BeanUtils.getBean(UserHistoryRepository.class);
        User user = (User) o;

        UserHistory userHistory = new UserHistory();
        userHistory.setUserId(user.getId());
        userHistory.setName(user.getName());
        userHistory.setEmail(user.getEmail());

        userHistoryRepository.save(userHistory);
    }
}
