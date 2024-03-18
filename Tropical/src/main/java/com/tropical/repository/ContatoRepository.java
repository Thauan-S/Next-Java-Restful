package com.tropical.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tropical.model.Contato;

public interface ContatoRepository extends JpaRepository<Contato, Long> {

	@Query("SELECT c  FROM Contato c WHERE c.assunto LIKE LOWER(CONCAT ('%',:assunto,'%'))")
    Page<Contato> findContatosByAssunto(@Param("assunto") String assunto, Pageable pageable);
}
