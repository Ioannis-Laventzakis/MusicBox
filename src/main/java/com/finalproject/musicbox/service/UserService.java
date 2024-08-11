package com.finalproject.musicbox.service;

import com.finalproject.musicbox.model.Role;
import com.finalproject.musicbox.model.User;
import com.finalproject.musicbox.repository.RoleRepository;
import com.finalproject.musicbox.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    public User createUser(User user, boolean isPremium) {
        Set<Role> roles = new HashSet<>();
        roles.add(roleRepository.findByName("USER"));  // Assign basic user role

        if (isPremium) {
            roles.add(roleRepository.findByName("PREMIUM"));  // Assign premium role if applicable
        }

        user.setRoles(roles);  // Set roles to user
        return userRepository.save(user);
    }

    public User findByName(String username) {
        return userRepository.findByUsername(username);

    // Other potential methods (e.g., findUserById, updateUser)...
    }
}
