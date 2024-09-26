package com.tropical.repository;


import java.util.Optional;

import com.tropical.model.Enterprise;
import org.springframework.data.jpa.repository.JpaRepository;

import com.tropical.model.User;


public interface EnterpriseRepository extends JpaRepository<Enterprise, Long> {
	Optional<Enterprise> findByUser(User user);
	Optional<Enterprise> findByUser_Email(String name);
//	@Query("SELECT c  FROM Cliente c WHERE c.nome LIKE LOWER(CONCAT ('%',:nome,'%'))")
//	Page<Cliente> findClientesByName(@Param("nome")String nome,Pageable pageable);
}
