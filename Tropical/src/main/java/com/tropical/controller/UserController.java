package com.tropical.controller;

import java.util.List;
import java.util.Set;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tropical.data.dto.CreateUserDto;
import com.tropical.data.dto.EmpresaDTO;
import com.tropical.exceptions.UserAlreadyExistsException;
import com.tropical.exceptions.UserAlreadyUsedException;
import com.tropical.model.Empresa;
import com.tropical.model.Role;
import com.tropical.model.User;
import com.tropical.repository.EmpresaRepository;
import com.tropical.repository.RoleRepository;
import com.tropical.repository.UserRepository;

@RestController
public class UserController {

	private final UserRepository userRepository;
	private final RoleRepository roleRepository;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	private final EmpresaRepository empresaRepository;

	public UserController(UserRepository userRepository, RoleRepository roleRepository,
			BCryptPasswordEncoder bCryptPasswordEncoder,EmpresaRepository empresaRepository) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
		this.empresaRepository = empresaRepository;
	}

	@PostMapping("/register/user")
	public ResponseEntity<Void> newUser(@RequestBody CreateUserDto createUserDto) {
		var role = roleRepository.findByName(Role.Values.BASIC.name());

		var userFromdb = userRepository.findByUsername(createUserDto.username());
		if (userFromdb.isPresent())
			throw new UserAlreadyExistsException();
		var user = new User();
		user.setUsername(createUserDto.username());
		user.setPassword(bCryptPasswordEncoder.encode(createUserDto.password()));
		user.setRoles(Set.of(role));
		userRepository.save(user);
		return ResponseEntity.ok().build();
	}

	@Transactional
	@PostMapping("/admin")
	public ResponseEntity<Void> newadmin(@RequestBody CreateUserDto createUserDto) {
		var adminRole = roleRepository.findByName(Role.Values.ADMIN.name());
		var userFromdb = userRepository.findByUsername(createUserDto.username());
		if (userFromdb.isPresent()) {
			throw new UserAlreadyExistsException();
		}
		var user = new User();
		user.setUsername(createUserDto.username());
		user.setPassword(bCryptPasswordEncoder.encode(createUserDto.password()));
		user.setRoles(Set.of(adminRole));
		userRepository.save(user);
		return ResponseEntity.ok().build();
	}
	@PostMapping("/register/empresa")
	public ResponseEntity<Void> newempresa(@RequestBody EmpresaDTO empresaDTO) {
		var companyRole=roleRepository.findByName(Role.Values.EMPRESA.name());
		var userDB= userRepository.findByUsername(empresaDTO.getUser().getUsername());
		if(userDB.isPresent()) {
			throw new UserAlreadyExistsException();
		}
		var user= new User();
		user.setUsername(empresaDTO.getUser().getUsername());
		user.setPassword(bCryptPasswordEncoder.encode(empresaDTO.getUser().getPassword()));
		user.setRoles(Set.of(companyRole));
		userRepository.save(user);
		
	Empresa empresa= new Empresa();
		
		if(empresaRepository.findByUser(user).isEmpty()) {
			empresa.setUser(user);
			empresa.setNomeEmpresa(empresaDTO.getNomeEmpresa());
			empresa.setCnpj(empresaDTO.getCnpj());
			empresa.setEndereco(empresaDTO.getEndereco());
			empresaRepository.save(empresa);
		}
		else {
			throw new UserAlreadyUsedException();
		}
		return ResponseEntity.ok().build();
	}

	@GetMapping("/users")
	@PreAuthorize("hasAuthority('SCOPE_ADMIN')")
	public ResponseEntity<List<User>> listUsers() {
		var users = userRepository.findAll();
		return ResponseEntity.ok(users);
	}
}
