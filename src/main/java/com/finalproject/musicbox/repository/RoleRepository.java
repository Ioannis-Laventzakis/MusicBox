package com.finalproject.musicbox.repository;

import com.finalproject.musicbox.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);  // Method to find a role by its name
}
