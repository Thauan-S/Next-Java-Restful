package com.tropical.services;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.tropical.data.dto.LoginRequest;
import com.tropical.model.Role;
import com.tropical.model.User;
import com.tropical.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;


@ExtendWith(MockitoExtension.class)
class TokenServiceTest {
    @Mock
    UserRepository userRepository;
    @Mock
    JwtEncoder jwtEncoder;
    @Mock
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @InjectMocks
    TokenService tokenService;
    @Captor
    ArgumentCaptor<String>stringArgumentCaptor;
    @Captor
    ArgumentCaptor<CharSequence>charSequenceArgumentCaptor;

    private LoginRequest loginRequest;
    private User user;
    private Role role;
    private String jwtToken = "mockJwtToken";

    @BeforeEach
    void setUp(){
        loginRequest=new LoginRequest("user","123");
        role = new Role();
        user = new User();
        user.setUserId(UUID.randomUUID());
        user.setEmail("username");
        user.setPassword("encodedPassword");
        user.setRoles(Set.of(role));
    }
    @Nested
    class login{
    @Test
    void shouldLogInSuccessFully() {
        //Arrange
        doReturn(Optional.of(user)).when(userRepository).findByEmail(stringArgumentCaptor.capture());
        doReturn(true).when(bCryptPasswordEncoder).matches(charSequenceArgumentCaptor.capture(),stringArgumentCaptor.capture());

        // Mock the JwtEncoder.encode method
        Jwt jwtMock = mock(Jwt.class);
        doReturn(jwtToken).when(jwtMock).getTokenValue();
        doReturn(jwtMock).when(jwtEncoder).encode(any(JwtEncoderParameters.class));

        // Act
        var outPut = tokenService.login(loginRequest);

        // Assert
        assertEquals(200, outPut.getStatusCodeValue());
        assertEquals(jwtToken, outPut.getBody().accessToken());

    }
    }
}