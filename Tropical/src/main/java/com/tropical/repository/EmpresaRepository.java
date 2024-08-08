package com.tropical.repository;


import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tropical.model.Empresa;
import com.tropical.model.User;


public interface EmpresaRepository extends JpaRepository<Empresa, Long> {
	Optional<Empresa> findByUser(User user);
	Optional<Empresa>findBynomeEmpresa(String nomeEmpresa);
//	@Query("SELECT c  FROM Cliente c WHERE c.nome LIKE LOWER(CONCAT ('%',:nome,'%'))")
//	Page<Cliente> findClientesByName(@Param("nome")String nome,Pageable pageable);
}
