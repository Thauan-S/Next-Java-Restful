package com.tropical.repository;


import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tropical.model.Cliente;


public interface ClienteRepository extends JpaRepository<Cliente, Long> {
	Optional<Cliente> findByUser_UserId(UUID user);
	
	@Query("SELECT c  FROM Cliente c WHERE c.nome LIKE LOWER(CONCAT ('%',:nome,'%'))")
	Page<Cliente> findClientesByName(@Param("nome")String nome,Pageable pageable);
}
