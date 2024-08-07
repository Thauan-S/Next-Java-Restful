package com.tropical.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tropical.data.dto.ClienteDto;
import com.tropical.data.dto.CreateUserDto;
import com.tropical.data.dto.EmpresaDTO;
import com.tropical.model.User;
import com.tropical.services.UserService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "Usuários" ,description = "Endpoint para criação de usuários")
public class UserController {

	@Autowired
	UserService userService;

	@PostMapping("/register/user")
	public ResponseEntity<Void> newUser(@RequestBody CreateUserDto createUserDto) {
		return userService.newUser(createUserDto);
	}

	@Transactional
	@PostMapping("/admin")
	public ResponseEntity<Void> newadmin(@RequestBody CreateUserDto createUserDto) {
		return userService.newadmin(createUserDto);
	}
	@PostMapping("/register/empresa")
	public ResponseEntity<Void> newEmpresa(@RequestBody EmpresaDTO empresaDTO) {
		
		return userService.newEmpresa(empresaDTO);
	}
	@PostMapping("/register/cliente")
	public ResponseEntity<Void> newCliente(@RequestBody ClienteDto clienteDto) {
		return userService.newCliente(clienteDto);
	}

	@GetMapping("/users")
	@PreAuthorize("hasAuthority('SCOPE_ADMIN')")
	public ResponseEntity<List<User>> listUsers() {
		return userService.listUsers();
	}
}
