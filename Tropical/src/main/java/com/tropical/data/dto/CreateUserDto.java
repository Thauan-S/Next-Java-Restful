package com.tropical.data.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record CreateUserDto(@NotBlank @Email String email, String password) {

}
