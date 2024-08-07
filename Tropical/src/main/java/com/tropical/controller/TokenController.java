package com.tropical.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tropical.data.dto.LoginRequest;
import com.tropical.data.dto.LoginResponse;
import com.tropical.services.TokenService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@RequestMapping("/api/auth/v1")
@Tag(name= "Login",description = "Endpoint para Fazer login")
public class TokenController {
	@Autowired
	TokenService tokenService;
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
		return tokenService.login(loginRequest);
	}
}
