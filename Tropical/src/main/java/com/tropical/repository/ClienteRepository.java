package com.tropical.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tropical.model.Cliente;
import com.tropical.model.User;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
	Optional<Cliente> findByUser_UserId(UUID user);
}
