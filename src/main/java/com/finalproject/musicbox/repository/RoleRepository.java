package com.finalproject.musicbox.repository;

import com.finalproject.musicbox.model.Role;

public interface RoleRepository {
    Role findByName(String name);
}
