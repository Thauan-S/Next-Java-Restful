package com.tropical.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tropical.model.TravelPackage;

import java.util.List;
import java.util.Optional;

public interface TravelPackageRepository extends JpaRepository<TravelPackage, Long> {
    List<TravelPackage> findByEnterprise_User_Email(String email);
}
