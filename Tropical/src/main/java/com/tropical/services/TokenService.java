package com.tropical.services;

import com.tropical.data.dto.LoginRequest;
import com.tropical.data.dto.LoginResponse;
import com.tropical.exceptions.ResourceNotFoundException;
import com.tropical.model.Role;
import com.tropical.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.Instant;
import java.util.stream.Collectors;

@Service
public class TokenService {
	private final UserRepository userRepository;
	private final JwtEncoder jwtEncoder;
	private BCryptPasswordEncoder passwordEncoder;
	public TokenService(JwtEncoder jwtEncoder,UserRepository userRepository,BCryptPasswordEncoder passwordEncoder) {
		
		this.userRepository = userRepository;
		this.jwtEncoder=jwtEncoder;
		this.passwordEncoder=passwordEncoder;
	}

	public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest){
		
		var user =userRepository.findByEmail(loginRequest.email());
		
		
		if(user.isEmpty() || !user.get().isLoginCorrect(loginRequest,passwordEncoder)) throw new ResourceNotFoundException("username or password is incorrect ");
		var now= Instant.now();
		var expiresIn=3600L;
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
