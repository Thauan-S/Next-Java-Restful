package com.tropical.services;

import com.tropical.model.Client;
import com.tropical.model.Role;
import com.tropical.model.User;
import com.tropical.repository.ClientRepository;
import com.tropical.repository.UserRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.webjars.NotFoundException;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ClientServiceTest {
    @Mock
    private ClientRepository clientRepository;
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private ClientService clientService;
    private Client client;

    @Captor
    private ArgumentCaptor<Client> clienteArgumentCaptor;
    @Captor
    private ArgumentCaptor<Long> IdArgumentCaptor;
    @Captor
    private ArgumentCaptor<PageRequest> PageRequestArgumentCaptor;
    @Captor
    private ArgumentCaptor<UUID> UuidArgumentCaptor;


    @BeforeEach
     void setUp(){
        var client = new Client();
        client.setClientId(1L);
        client.setName("Thauan");
                client.setPhone("73988896878");
                client.setBirthday(new Date(2002 - 17 - 11));
                client.setZipCode("45330000");
    }
    @Nested
    class createClient {
        @Test
        @DisplayName("Should returns a client with success")
        void shouldGetAClientWithSuccess() {
            //Arrange
            doReturn(Optional.of(client))
                    .when(clientRepository)
                    .findById(IdArgumentCaptor.capture());

            //Act
            var outPut = clientService.findById(1L);

            //Assert
            //assertNotNull(outPut.getBody().getClientId());
            assertEquals("Thauan", outPut.getBody().getName());
            assertEquals(client.getName(), outPut.getBody().getName());
        }

        @Test
        @DisplayName("Should throws a not found exception when the client not exists in the data base")
        void shouldThrowsANotFoundException() {
            //Arrange
            var clientId = 1L;
            doReturn(Optional.empty()).when(clientRepository).findById(IdArgumentCaptor.capture());
            //Act
            Optional<Client> outPut = clientRepository.findById(1L);

            //Assert
            //assertNotNull(outPut.getClienteId());
            NotFoundException exception = assertThrows(NotFoundException.class, () -> clientService.findById(clientId));

            assertThrows(NotFoundException.class, () -> clientService.findById(1L));
            assertEquals("The client  id :" + clientId + "does not  exist  in the data base", exception.getMessage());
        }
    }

    @Nested
    class findClient {

        @Test
        void shouldGetAClientByIdWithSuccess() {
            //Arrange
            var cliente = new Client();
            doReturn(Optional.of(cliente))
                    .when(clientRepository)
                    .findById(IdArgumentCaptor.capture());
            //Act
            var outPut = clientRepository.findById(cliente.getClientId());

            //Assert
            assertTrue(outPut.isPresent());
            assertEquals(1L, outPut.get().getClientId());
            assertEquals("Thauan", outPut.get().getName());
            assertEquals("73988896878", outPut.get().getPhone());
            assertEquals(new Date(2002 - 17 - 11), outPut.get().getBirthday());
            assertEquals("45330000", outPut.get().getZipCode());
        }

        @Test
        void shouldGetAClientByIdWithSuccessWhenOptionalIsEmpty() {
            //Arrange
            doReturn(Optional.empty())
                    .when(clientRepository)
                    .findById(IdArgumentCaptor.capture());
            //Act
            var outPut = clientRepository.findById(client.getClientId());

            //Assert
            assertNotNull(outPut);
            assertTrue(outPut.isEmpty());

        }

    }

    @Nested
    class listClient {
        @Test
        void shouldReturnAllClientsWithSuccess() {
//            //Arrange
//            var cliente1 = new Customer(1L, "Thauan", "73988896878", new Date(2002 - 17 - 11), "45330000");
//            var cliente2 = new Customer(2L, "Thauan2", "73988896877", new Date(2002 - 17 - 12), "45330001");
//            //Page<Cliente>page=new Page.of(cliente);
//            List<Customer> clientesList = Arrays.asList(cliente1, cliente2);
//
//            Page<Customer> clientePage = new PageImpl<>(clientesList, PageRequest.of(0, 2), clientesList.size());
//            //doReturn(page.get().toList())
//            doReturn(clientePage)
//                    .when(clienteRepository)
//                    .findAll(PageRequestArgumentCaptor.capture());
//
//            //Act
//            var output = clienteService.findAll(1, 2, "ASC");
//
//
//            //Assert
//            assertNotNull(output);
//            assertEquals(clientesList.size(), Objects.requireNonNull(output.getBody()).size());
//            assertEquals(clientesList.get(0).getClienteId(), cliente1.getClienteId());
//            assertEquals(clientesList.get(1).getClienteId(), cliente2.getClienteId());
//            assertEquals(clientePage.getSize(), clientesList.size());
        }


    }

    @Nested
    class deleteById {
        @Test
        void shouldDeleteClientById() {
            //Arrange
//            Long IdToDelete = 1L;
//            var cliente = new Customer(1L, "Thauan", "73988896878", new Date(2002 - 17 - 11), "45330000");
//            Role admin = new Role();
//            admin.setRoleId(1L);
//            Role basic = new Role();
//            admin.setRoleId(2L);
//            Role empresa = new Role();
//            admin.setRoleId(3L);
//            Set<Role> roles = Set.of(admin, basic, empresa);
//
//            var jwt = new JwtAuthenticationToken(null, List.of(new SimpleGrantedAuthority("ROLE_USER")), UUID.randomUUID().toString());
//
//            User user = new User(
//                    UUID.randomUUID(),
//                    "username",
//                    "password",
//                    cliente,
//                    roles);
//            doReturn(Optional.of(user)).when(userRepository).findById(UuidArgumentCaptor.capture());
//            doReturn(cliente).when(clienteRepository).findById(IdArgumentCaptor.capture());
//            doNothing().when(clienteRepository).deleteById(IdArgumentCaptor.capture());
//            //Act
//            clienteService.delete(1L, jwt);
//            //Assert
//            verify(clienteRepository).deleteById(IdToDelete);
        }


    }
}
