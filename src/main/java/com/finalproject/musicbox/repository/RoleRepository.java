package com.finalproject.musicbox.repository;

import com.finalproject.musicbox.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for managing Role entities.
 * Provides methods for performing CRUD operations on Role entities.
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    /**
     * Finds a role by its name.
     *
     * @param name the name of the role to find
     * @return the role with the specified name, or null if no role is found
     */
    Role findByName(String name);
}