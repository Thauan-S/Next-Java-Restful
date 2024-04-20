package com.tropical.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tropical.model.PacoteDeViagem;

public interface PacoteRepository extends JpaRepository<PacoteDeViagem, Long> {

}
