package com.tropical.data.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tropical.model.Client;
import com.tropical.model.Role;
import com.tropical.model.User;
import jakarta.persistence.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;
import java.util.UUID;


public class UserDto {
    
    private UUID userId;
    private String email;
    private String password;


    private Client client;

    private Set<Role> roles;




	public UserDto() {
	}
    public UserDto(String email, String password) {
        this.email = email;
        this.password = password;
    }
	public UserDto(UUID userId, String email, String password, Client client, Set<Role> roles) {
		this.userId = userId;
		this.email = email;
		this.password = password;
		this.client = client;
		this.roles = roles;
	}

    public UserDto(User user) {
        this.userId = user.getUserId();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.roles = user.getRoles();
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public boolean isLoginCorrect(LoginRequest loginRequest, PasswordEncoder passwordEncoder) {
        return passwordEncoder.matches(loginRequest.password(), this.password);
    }
}