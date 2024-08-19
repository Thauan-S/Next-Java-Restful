package com.tropical.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tropical.model.TravelPackage;

public interface TravelPackageRepository extends JpaRepository<TravelPackage, Long> {

}
