package com.tropical.repository;


import com.tropical.model.Client;
import com.tropical.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface ClientRepository extends JpaRepository<Client, Long> {
	Optional<Client> findByUser(User user);
	Optional<Client> findByUser_email(String email);

}
