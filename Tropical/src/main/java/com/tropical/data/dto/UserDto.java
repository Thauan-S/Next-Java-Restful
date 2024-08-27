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


    private String username;
    @JsonIgnore
    private String password;


    private Client client;

    private Set<Role> roles;




	public UserDto() {
	}
    public UserDto(String username, String password) {
        this.username = username;
        this.password = password;
    }
	public UserDto(UUID userId, String username, String password, Client client, Set<Role> roles) {
		this.userId = userId;
		this.username = username;
		this.password = password;
		this.client = client;
		this.roles = roles;
	}

    public UserDto(User user) {
        this.userId = user.getUserId();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.roles = user.getRoles();
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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