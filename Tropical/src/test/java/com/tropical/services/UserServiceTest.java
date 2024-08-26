package com.tropical.services;

import com.tropical.data.dto.ClientDto;
import com.tropical.data.dto.CreateUserDto;
import com.tropical.data.dto.EnterpriseDto;
import com.tropical.exceptions.UserAlreadyExistsException;
import com.tropical.model.Client;
import com.tropical.model.Enterprise;
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
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.testcontainers.shaded.org.checkerframework.checker.units.qual.C;

import java.util.List;
import java.util.Optional;
import java.util.Set;
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
    private User userAdmin;
    private User enterpriseUser;
    private User clientUser;
    private Role role;
    private Role basicRole;
    private Role enterpriseRole;
    private Role adminRole;
    private ClientDto clientDto;
    private CreateUserDto createUserDto;
    private CreateUserDto createAdminUserDto;
    private EnterpriseDto enterpriseDto;

    @Captor
    ArgumentCaptor<UUID> uuidArgumentCaptor;
    @Captor
    ArgumentCaptor<String> stringArgumentCaptor;
    @Captor
    ArgumentCaptor<User> userArgumentCaptor;
    @Captor
    ArgumentCaptor<CharSequence> charSequenceArgumentCaptor;
    @Captor
    ArgumentCaptor<Client> clientArgumentCaptor;
    @Captor
    ArgumentCaptor<Enterprise> enterpriseArgumentCaptor;

    @BeforeEach
    void setUp() {


        user = new User();

        userAdmin = new User();

        role = new Role();
        role.setName(Role.Values.BASIC.name());
        role.setRoleId(Role.Values.BASIC.getRoleId());

        basicRole =new Role();
        basicRole.setName(Role.Values.BASIC.name());
        basicRole.setRoleId(Role.Values.BASIC.getRoleId());

        enterpriseRole = new Role();
        enterpriseRole.setRoleId(Role.Values.EMPRESA.getRoleId());
        enterpriseRole.setName(Role.Values.EMPRESA.name());

        adminRole = new Role();
        adminRole.setName(Role.Values.ADMIN.name());
        adminRole.setRoleId(Role.Values.ADMIN.getRoleId());

        createUserDto = new CreateUserDto("thau", "123");
        createAdminUserDto = new CreateUserDto("admin", "123");
        enterpriseDto = new EnterpriseDto();
        enterpriseUser = new User();
        enterpriseUser.setUsername("enterprise");
        enterpriseUser.setPassword("123");
        enterpriseDto.setUser(enterpriseUser);

        clientUser=new User();
        clientUser.setUsername("client");
        clientUser.setPassword("123");
        clientDto=new ClientDto();
        clientDto.setUser(clientUser);
    }

    @Nested
    class createUser {
        @Test
        @DisplayName("Should create a user with success")
        void shouldCreateUser() {
            //Arrange
            doReturn(role).when(roleRepository).findByName(stringArgumentCaptor.capture());
            //Act
            var outPut = userService.newUser(createUserDto);
            //Assert
            assertNotNull(outPut);
        }

        @Test
        @DisplayName("Should throws a UserAlreadyExistsException when user exists")
        void shouldThrowsAException() {
            //Arrange
            doReturn(Optional.of(user)).when(userRepository).findByUsername(stringArgumentCaptor.capture());
            //Act & Assert
            assertThrows(UserAlreadyExistsException.class, () -> userService.newUser(createUserDto));
        }
    }

    @Nested
    class createAdmin {
        @DisplayName("Should create a admin with success")
        @Test
        void createNewAdmin() {
            // Arrange
            when(userRepository.findByUsername(stringArgumentCaptor.capture())).thenReturn(Optional.empty());
            when(roleRepository.findByName(stringArgumentCaptor.capture())).thenReturn(adminRole);
            when(bCryptPasswordEncoder.encode(stringArgumentCaptor.capture())).thenReturn("encodedPassword");

            // Act
            var output = userService.createNewAdmin(createAdminUserDto);

            // Assert
            verify(userRepository).save(userArgumentCaptor.capture());
            User savedUser = userArgumentCaptor.getValue();

            assertEquals(createAdminUserDto.username(), savedUser.getUsername());
            assertEquals("encodedPassword", savedUser.getPassword());
            assertEquals(Set.of(adminRole), savedUser.getRoles());
            assertEquals(ResponseEntity.ok().build(), output);
        }
    }

    @Nested
    class createNewEnterprise {
        @Test
        void createNewEnterprise() {
            //Arrange
            when(userRepository.findByUsername(stringArgumentCaptor.capture())).thenReturn(Optional.empty());
            when(roleRepository.findByName(stringArgumentCaptor.capture())).thenReturn(adminRole);
            when(bCryptPasswordEncoder.encode(stringArgumentCaptor.capture())).thenReturn("encodedPassword");
            //Act
            var outPut = userService.createNewEnterprise(enterpriseDto);
            //Assert
            assertNotNull(outPut);
            verify(enterpriseRepository, times(1)).save(enterpriseArgumentCaptor.capture());

        }

    }

    @Nested
    class newClient {
        @Test
        @DisplayName("Should create a client with success")
        void shouldCreateAclientWithSuccess() {
            //Arrange
            doReturn(basicRole).when(roleRepository).findByName(stringArgumentCaptor.capture());
            doReturn(Optional.empty()).when(userRepository).findByUsername(stringArgumentCaptor.capture());
            when(bCryptPasswordEncoder.encode(stringArgumentCaptor.capture())).thenReturn("encodedPassword");
            //Act
            var outPut=userService.newClient(clientDto);
            //Assert
            verify(clientRepository,times(1)).save(clientArgumentCaptor.capture());
        }
    }
    @Nested
    class findAll{
        @Test
        @DisplayName("Should returns a List of all Users with success")
        void listUsers() {
            //Arrange
            doReturn(List.of(user)).when(userRepository).findAll();
            //Act
            var outPut=userService.listUsers();
            //Assert
            assertEquals(ResponseEntity.ok(List.of(user)),outPut);
            verify(userRepository,times(1)).findAll();
        }
    }

}