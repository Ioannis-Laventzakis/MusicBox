package com.finalproject.musicbox.service;

import com.finalproject.musicbox.model.Role;
import com.finalproject.musicbox.model.Subscription;
import com.finalproject.musicbox.model.User;
import com.finalproject.musicbox.repository.RoleRepository;
import com.finalproject.musicbox.repository.UserRepository;
import com.finalproject.musicbox.repository.SubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final SubscriptionRepository subscriptionRepository;

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository, SubscriptionRepository subscriptionRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.subscriptionRepository = subscriptionRepository;
    }

    public User createUser(User user) {
        user.setStatus("ACTIVE");
        return userRepository.save(user);
    }

    public Subscription createSubscription(User user, LocalDateTime startDate, LocalDateTime endDate) {
        Subscription subscription = new Subscription();
        subscription.setUser(user);
        subscription.setStartDate(startDate);
        subscription.setEndDate(endDate);
        return subscriptionRepository.save(subscription);
    }

    public Optional<User> findUserById(Long id) {
        return userRepository.findById(id);
    }

    public User updateUser(User user) {
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
