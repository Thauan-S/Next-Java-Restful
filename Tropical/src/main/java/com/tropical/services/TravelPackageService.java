package com.tropical.services;


import com.tropical.data.dto.TravelPackageDto;
import com.tropical.exceptions.ForbiddenAccesException;
import com.tropical.exceptions.ResourceNotFoundException;
import com.tropical.model.Role;
import com.tropical.model.TravelPackage;
import com.tropical.repository.EnterpriseRepository;
import com.tropical.repository.TravelPackageRepository;
import com.tropical.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;


@Service
public class TravelPackageService {
    private final TravelPackageRepository travelPackageRepository;
    private final UserRepository userRepository;
    private final EnterpriseRepository enterpriseRepository;

    public TravelPackageService(TravelPackageRepository travelPackageRepository, UserRepository userRepository, EnterpriseRepository enterpriseRepository) {
        this.travelPackageRepository = travelPackageRepository;
        this.userRepository = userRepository;
        this.enterpriseRepository = enterpriseRepository;
    }


    public Page<TravelPackageDto> findAll(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "12") int size,
            @RequestParam(name = "direction", defaultValue = "asc") String direction) {

        var travelPackages = travelPackageRepository.findAll(PageRequest.of(page, size, Direction.valueOf(direction), "destiny"))
                .map(p -> new TravelPackage(p));
        return TravelPackageDto.packagesList(travelPackages);
    }

    @Transactional
    public TravelPackageDto create(@RequestBody TravelPackageDto travelPackageDto, JwtAuthenticationToken token) {
        var user = userRepository.findById(UUID.fromString(token.getName())).orElseThrow(() -> new ResourceNotFoundException("The user id : " + token.getName() + "does not exists in the data base"));
        var enterprise = enterpriseRepository.findByname(travelPackageDto.getEnterprise().getName()).orElseThrow(() -> new ResourceNotFoundException("The enterprise  by id " + travelPackageDto.getEnterprise().getEnterpriseId() + "does not exists in the data base"));
        ;
        var isAdmin = user.getRoles().stream()
                .anyMatch(role -> role.getName().equalsIgnoreCase(Role.Values.ADMIN.name()));
        var isEnterprise = user.getRoles().stream()
                .anyMatch(role -> role.getName().equalsIgnoreCase(Role.Values.EMPRESA.name()));
        if (isAdmin || isEnterprise) {
            TravelPackage travelPackage = new TravelPackage();
            travelPackage.setDescription(travelPackageDto.getDescription());
            travelPackage.setDestiny(travelPackageDto.getDestiny());
            travelPackage.setDays(travelPackageDto.getDays());
            travelPackage.setCategory(travelPackageDto.getCategory());
            travelPackage.setEnterprise(enterprise);
            travelPackage.setImage(travelPackageDto.getImage());
            travelPackage.setPrice(travelPackageDto.getPrice());
            travelPackageRepository.save(travelPackage);
            return travelPackageDto;
        } else {
            throw new ForbiddenAccesException();
        }

    }

    public TravelPackageDto findById(@PathVariable Long id, JwtAuthenticationToken token) {
        var travelPackage = travelPackageRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("The package by " + id + " does not exists in the data base"));
        return new TravelPackageDto(travelPackage);
    }
    @Transactional
    public TravelPackageDto update(@RequestBody TravelPackageDto travelPackageDto, JwtAuthenticationToken token) {
        var user = userRepository.findById(UUID.fromString(token.getName()));
        var isAdmin = user.get().getRoles().stream()
                .anyMatch(role -> role.getName().equalsIgnoreCase(Role.Values.ADMIN.name()));
        if (isAdmin || travelPackageDto.getEnterprise().getUser().getUserId().equals(UUID.fromString(token.getName()))) {

            var packageDb = travelPackageRepository.findById(travelPackageDto.getId()).orElseThrow(() -> new ResourceNotFoundException("The package by id : " + travelPackageDto.getId() + " does not found in the data base"));

            packageDb.setDestiny(travelPackageDto.getDestiny());
            packageDb.setDescription(travelPackageDto.getDescription());
            packageDb.setCategory(travelPackageDto.getCategory());
            packageDb.setDays(travelPackageDto.getDays());
            packageDb.setImage(travelPackageDto.getImage());
            packageDb.setPrice(travelPackageDto.getPrice());
            travelPackageRepository.save(packageDb);
        }
        return travelPackageDto;
    }
    @Transactional
    public ResponseEntity<?> delete(@PathVariable Long id, JwtAuthenticationToken token) {
        var user = userRepository.findById(UUID.fromString(token.getName())).orElseThrow(() -> new ResourceNotFoundException("Package id :" + id + "was not found in the database"));
        var isAdmin = user.getRoles().stream()
                .anyMatch(role -> role.getName().equalsIgnoreCase(Role.Values.ADMIN.name()));
        var travelPackage =
        travelPackageRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Package id :" + id + "was not found in the database"));
        if (isAdmin || travelPackage.getEnterprise().getUser().getUserId().equals(UUID.fromString(token.getName()))){
            travelPackageRepository.delete(travelPackage);
            return ResponseEntity.noContent().build();
        } else{
            throw new ForbiddenAccesException();
        }

    }
}