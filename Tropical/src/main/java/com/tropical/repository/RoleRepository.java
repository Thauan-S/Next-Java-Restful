package com.tropical.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tropical.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
 Role findByName(String name);
}
