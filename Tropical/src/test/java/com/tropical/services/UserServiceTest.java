package com.tropical.services;

import com.tropical.data.dto.CreateUserDto;
import com.tropical.exceptions.UserAlreadyExistsException;
import com.tropical.model.Role;
import com.tropical.model.User;
import com.tropical.repository.ClientRepository;
import com.tropical.repository.EnterpriseRepository;
import com.tropical.repository.RoleRepository;
import com.tropical.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private RoleRepository roleRepository;
    @Mock
    private EnterpriseRepository enterpriseRepository;
    @Mock
    private ClientRepository clientRepository;
    @Mock
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @InjectMocks
    private UserService userService;
    private User user;
    private Role role;
    private CreateUserDto createUserDto;

    @Captor
    ArgumentCaptor<UUID>uuidArgumentCaptor;
    @Captor
    ArgumentCaptor<String>stringArgumentCaptor;
    @BeforeEach
    void setUp() {
        user = new User();
        role=new Role();
        role.setName(Role.Values.BASIC.name());
        role.setRoleId(Role.Values.BASIC.getRoleId());
        createUserDto=new CreateUserDto("thau","123");
    }

    @Nested
    class createUser {
        @Test
        @DisplayName("Should create a user with success")
        void shouldCreateUser() {
            //Arrange
            doReturn(role).when(roleRepository).findByName(stringArgumentCaptor.capture());

            //Act
            var outPut=userService.newUser(createUserDto);
            //Assert
            assertNotNull(outPut);
        }
        @Test
        @DisplayName("Should throws a UserAlreadyExistsException when user exists")
        void shouldThrowsAException() {
            //Arrange
            doReturn(Optional.of(user)).when(userRepository).findByUsername(stringArgumentCaptor.capture());
            //Act & Assert
            assertThrows(UserAlreadyExistsException.class,()-> userService.newUser(createUserDto));
        }
    }

    @Nested
    class createAdmin{
        @DisplayName("Should create a admin with success")
    void createNewAdmin() {

    }
    }

    @Test
    void createNewEnterprise() {
    }

    @Test
    void newClient() {
    }

    @Test
    void listUsers() {
    }
}