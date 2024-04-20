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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@Tag(name= "Login",description = "Endpoint para Fazer login")
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
	@Operation(
			summary = "Login",
			description = "Login",
			tags = {"Login"},
			responses = {
					@ApiResponse(description="Success",responseCode = "200"
							,content = @Content(schema = @Schema(implementation = LoginResponse.class))),
					@ApiResponse(description="No Content",responseCode = "204",content = @Content),
					@ApiResponse(description="Bad Request",responseCode = "400",content = @Content),
					@ApiResponse(description="Not Found",responseCode = "404",content = @Content),
					@ApiResponse(description="Internal Server Error",responseCode = "204",content = @Content)
			}
			)
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
