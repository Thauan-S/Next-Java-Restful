package com.tropical.controller;

import java.time.Instant;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.webjars.NotFoundException;

import com.tropical.data.dto.LoginRequest;
import com.tropical.data.dto.LoginResponse;
import com.tropical.model.Role;
import com.tropical.repository.UserRepository;


@RestController
public class TokenController {
	private final UserRepository userRepository;
	private final JwtEncoder jwtEncoder;
	private BCryptPasswordEncoder passwordEncoder;
	public TokenController(JwtEncoder jwtEncoder,UserRepository userRepository,BCryptPasswordEncoder passwordEncoder) {
		
		this.userRepository = userRepository;
		this.jwtEncoder=jwtEncoder;
		this.passwordEncoder=passwordEncoder;
	}
	@PostMapping("/login")
	public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest){
		
		var user =userRepository.findByUsername(loginRequest.username());
		
		
		if(user.isEmpty() || !user.get().isLoginCorrect(loginRequest,passwordEncoder)) throw new NotFoundException("O usuario ou senha estão inválidos");
		var now= Instant.now();
		var expiresIn=300L;
		var scopes= user.get().getRoles()
				.stream()
				.map(Role::getName)
				.collect(Collectors.joining(" "));
				
		var claims=JwtClaimsSet.builder()
			.issuer("mybackEnd")
			.subject(user.get().getUserId().toString())
			.issuedAt(now)
			.expiresAt(now.plusSeconds(expiresIn))
			.claim("scope", scopes)
			.build();
		
		var jwtValue=jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
		return ResponseEntity.ok(new LoginResponse(jwtValue, expiresIn));
			
	}
}
