package com.tropical.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.tropical.model.Reserve;

import java.util.List;


public interface ReserveRepository extends JpaRepository<Reserve, Long> {

    List<Reserve> findByClient_User_Email(String email);
}
