package com.tropical.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tropical.model.TravelPackage;

import java.util.Optional;

public interface TravelPackageRepository extends JpaRepository<TravelPackage, Long> {
    Optional<TravelPackage> findByEnterprise_User_Email(String email);
}
