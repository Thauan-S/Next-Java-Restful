package com.tropical.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tropical.model.PacoteDeViagem;

@Repository
public interface PacoteRepository extends JpaRepository<PacoteDeViagem, Long>  {

}
