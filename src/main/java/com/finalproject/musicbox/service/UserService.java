package com.finalproject.musicbox.service;

import com.finalproject.musicbox.model.Role;
import com.finalproject.musicbox.model.User;
import com.finalproject.musicbox.repository.RoleRepository;
import com.finalproject.musicbox.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public User createUser(User user, boolean isPremium) {
        // Assign default role
        Role userRole = roleRepository.findByName("USER");
        user.setStatus("ACTIVE"); // Set default status
        if (isPremium) {
            user.setIsPremium(true);
            Role premiumRole = roleRepository.findByName("PREMIUM");
            // Logic to assign the premium role if necessary
        } else {
            user.setIsPremium(false);
        }
        return userRepository.save(user);
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
