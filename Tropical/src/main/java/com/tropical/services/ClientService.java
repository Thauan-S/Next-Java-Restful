package com.tropical.services;

import com.tropical.data.dto.ClientDto;
import com.tropical.data.dto.ClientItemDto;
import com.tropical.data.dto.ClientListDto;
import com.tropical.exceptions.ForbiddenAccesException;
import com.tropical.exceptions.ResourceNotFoundException;
import com.tropical.model.Role;
import com.tropical.repository.ClientRepository;
import com.tropical.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


import java.nio.file.AccessDeniedException;
import java.util.UUID;

@Service
public class ClientService {

    private final ClientRepository clientRepository;
    private final UserRepository userRepository;

    public ClientService(ClientRepository clientRepository, UserRepository userRepository) {
        this.clientRepository = clientRepository;
        this.userRepository = userRepository;
    }


    public ResponseEntity<ClientDto> findById(@PathVariable Long id) {
        var client = clientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("The client  id :" + id + "does not  exist  in the data base"));

        return ResponseEntity.ok(new ClientDto(client));
    }

    public ResponseEntity<ClientListDto> findAll(@RequestParam(value = "page", defaultValue = "0") int page,
                                                 @RequestParam(value = "size", defaultValue = "12") int size,
                                                 @RequestParam(value = "direction", defaultValue = "ASC") String direction) {
        var clients = clientRepository.findAll(PageRequest.of(page, size, Direction.valueOf(direction), "name"))
                .map(client -> new ClientItemDto(client.getClientId(), client.getName(), client.getPhone(),
                        client.getBirthday(), client.getZipCode(), client.getUser()));

        return ResponseEntity.ok(new ClientListDto(clients.getContent(), page, size, clients.getTotalPages(),
                clients.getTotalElements()));
    }
    @Transactional
    public ClientDto update(@RequestBody ClientDto clientDto, JwtAuthenticationToken token)
             {
        var user = userRepository.findById(UUID.fromString(token.getName()));
        var clientbd = clientRepository.findById(clientDto.getClientId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Client id:" + clientDto.getClientId() + " Not Found in the data base"));
        var isAdmin = user.get().getRoles().stream()
                .anyMatch(role -> role.getName().equalsIgnoreCase(Role.Values.ADMIN.name()));
        if (isAdmin || clientbd.getUser().getUserId().equals(UUID.fromString(token.getName()))) {

            clientbd.setName(clientDto.getName());
            clientbd.setBirthday(clientDto.getBirthday());
            clientbd.setPhone(clientDto.getPhone());
            clientbd.setZipCode(clientDto.getZipCode());
            clientRepository.save(clientbd);
            return new ClientDto(clientbd);
        } else {
            throw new ForbiddenAccesException("");
        }
    }
    @Transactional
    public ResponseEntity<?> delete(@PathVariable Long id, JwtAuthenticationToken token) {
        var user = userRepository.findById(UUID.fromString(token.getName()));
        var clibd = clientRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Client id: " + id + "does not found in the data base"));
        var isAdmin = user.get().getRoles().stream()
                .anyMatch(role -> role.getName().equalsIgnoreCase(Role.Values.ADMIN.name()));
        if (isAdmin || clibd.getUser().getUserId().equals(UUID.fromString(token.getName()))) {
            clientRepository.deleteById(id);
        } else {
            throw new ForbiddenAccesException("The user does not have permission to perform this action");
        }

        return ResponseEntity.noContent().build();
    }
}
