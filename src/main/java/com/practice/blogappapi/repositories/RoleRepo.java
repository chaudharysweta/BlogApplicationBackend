package com.practice.blogappapi.repositories;

import com.practice.blogappapi.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<Role,Integer> {
}
