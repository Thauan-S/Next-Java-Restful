package com.tropical.repository;


import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tropical.model.Cliente;
import com.tropical.model.User;


public interface ClienteRepository extends JpaRepository<Cliente, Long> {
	Optional<Cliente> findByUser(User user);
	

}
