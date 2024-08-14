package com.tropical.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.tropical.model.Reserva;

import java.util.List;
import java.util.Optional;


public interface ReservaRepository extends JpaRepository<Reserva, Long> {

    List<Reserva> findByCliente_User_username(String username);
}
