package com.tropical.services;

import com.tropical.data.dto.EnterpriseDto;
import com.tropical.exceptions.ForbiddenAccesException;
import com.tropical.exceptions.ResourceNotFoundException;
import com.tropical.model.Enterprise;
import com.tropical.model.Role;
import com.tropical.repository.EnterpriseRepository;
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
public class EnterpriseService {

    private final EnterpriseRepository enterpriseRepository;
    private final UserRepository userRepository;

    public EnterpriseService(EnterpriseRepository enterpriseRepository, UserRepository userRepository) {
        this.enterpriseRepository = enterpriseRepository;
        this.userRepository = userRepository;
    }

    public ResponseEntity<EnterpriseDto> findById(@PathVariable Long id) {
        var enterprise = enterpriseRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("The company  id" + id + "does not exists in data base"));
        return ResponseEntity.ok(new EnterpriseDto(enterprise));
    }


    public Page<EnterpriseDto> findAll(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "12") int size,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction) {
        var enterprises = enterpriseRepository.findAll(PageRequest.of(page, size, Direction.valueOf(direction), "name"))
                .map(enterprise -> new Enterprise(
                        enterprise.getEnterpriseId(),
                        enterprise.getName(),
                        enterprise.getCnpj(),
                        enterprise.getAddress(),
                        enterprise.getUser(),
                        enterprise.getTravelPackage()
                ));
        return EnterpriseDto.enterpriseList(enterprises);
    }
    @Transactional
    public EnterpriseDto update(@RequestBody EnterpriseDto enterpriseDto, JwtAuthenticationToken token) {
        var user = userRepository.findById(UUID.fromString(token.getName()));
        var enterpriseBd = enterpriseRepository.findById(enterpriseDto.getEnterpriseId()).orElseThrow(() -> new ResourceNotFoundException("Enterprise does not found in the data base"));
        var isAdmin = user.get().getRoles()
                .stream()
                .anyMatch(role -> role.getName().equalsIgnoreCase(Role.Values.ADMIN.name()));
        if (isAdmin || enterpriseBd.getUser().getUserId().equals(UUID.fromString(token.getName()))) {

            enterpriseBd.setName(enterpriseDto.getName());
            enterpriseBd.setAddress(enterpriseDto.getAddress());
            enterpriseBd.setCnpj(enterpriseDto.getCnpj());
            enterpriseRepository.save(enterpriseBd);
            return new EnterpriseDto(enterpriseBd);
        } else {
            throw new ForbiddenAccesException("The user does not have permission");
        }

    }
    @Transactional
    public ResponseEntity<?> delete(@PathVariable Long id, JwtAuthenticationToken token) {

        var user = userRepository.findById(UUID.fromString(token.getName()));
        System.out.println("usuário" + user.get().getUserId());
        var isAdmin = user.get().getRoles()
                .stream()
                .anyMatch(role -> role.getName().equalsIgnoreCase(Role.Values.ADMIN.name()));
        var enterpriseBd = enterpriseRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("The company  id:" + id + "Is not in the database"));
        if (isAdmin || enterpriseBd.getUser().getUserId().equals(user.get().getUserId())) {
            enterpriseRepository.deleteById(id);
        } else {
            throw new ForbiddenAccesException("The user :" + user.get().getUsername() + " does not have permission to perform this operation ");
        }
        return ResponseEntity.noContent().build();
    }
}
