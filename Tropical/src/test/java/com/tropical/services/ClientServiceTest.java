package com.tropical.services;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.tropical.data.dto.ClientDto;
import com.tropical.exceptions.ForbiddenAccesException;
import com.tropical.exceptions.ResourceNotFoundException;
import com.tropical.model.Client;
import com.tropical.model.Reserve;
import com.tropical.model.Role;
import com.tropical.model.User;
import com.tropical.repository.ClientRepository;
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
import org.springframework.data.domain.*;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

import java.util.*;

import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
public class ClientServiceTest {
    @Mock
    private ClientRepository clientRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private JwtAuthenticationToken token;
    @InjectMocks
    private ClientService clientService;

    private Client client;
    private Client client2;
    private User commomUser;
    private  Role basicRole;
    private Reserve reserve;
    private ClientDto clientDto;
    @Captor
    private ArgumentCaptor<Client> clientArgumentCaptor;
    @Captor
    private ArgumentCaptor<Long> idArgumentCaptor;
    @Captor
    private ArgumentCaptor<PageRequest> pageRequestArgumentCaptor;
    @Captor
    private ArgumentCaptor<UUID> uuidArgumentCaptor;

    @BeforeEach
     void setUp(){

        reserve=new Reserve();

        basicRole =new Role();
        basicRole.setRoleId(1L);
        basicRole.setName("BASIC");



        commomUser = new User();
        commomUser.setRoles(Set.of(basicRole));
        commomUser.setUserId(UUID.randomUUID());
        commomUser.setUsername("thau");
        commomUser.setPassword("123");



        client = new Client();
        client.setClientId(1L);
        client.setName("Thauan");
        client.setPhone("73988896878");
        client.setBirthday(new Date(2002 - 17 - 11));
        client.setZipCode("45330000");
        client.setUser(commomUser);
        client.setReserves(List.of(reserve));

        client2 = new Client();
        client2.setClientId(2L);
        client2.setName("Thau");
        client2.setPhone("111111");
        client2.setBirthday(new Date(2002 - 11 - 11));
        client2.setZipCode("4533");
        client2.setReserves(List.of(reserve));

        clientDto=new ClientDto(1L,"thau","7988896878",new Date(2002-17-11),"45330000",commomUser);
        token=mock(JwtAuthenticationToken.class);
        lenient().when(token.getName()).thenReturn(commomUser.getUserId().toString());
    }
    @Nested
    class findClientById {
        @Test
        @DisplayName("Should returns a client by id with success")
        void shouldGetAClientWithSuccess() {
            //Arrange
            doReturn(Optional.of(client)).when(clientRepository).findById(idArgumentCaptor.capture());
            //Act
            var outPut = clientService.findById(1L);

            //Assert
            verify(clientRepository).findById(idArgumentCaptor.capture());
            assertEquals(1L, idArgumentCaptor.getValue());
            assertNotNull(outPut.getBody().getClientId());
            assertEquals("Thauan", outPut.getBody().getName());
            assertEquals(client.getName(), outPut.getBody().getName());
        }

        @Test
        @DisplayName("Should throws a not found exception when the client not exists in the data base")
        void shouldThrowsANotFoundException() {
            //Arrange
            var clientId = 1L;
            doReturn(Optional.empty()).when(clientRepository).findById(idArgumentCaptor.capture());
            //Act
            Optional<Client> outPut = clientRepository.findById(1L);

            //Assert
            ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> clientService.findById(clientId));
            assertThrows(ResourceNotFoundException.class, () -> clientService.findById(1L));
            assertEquals("The client  id :" + clientId + "does not  exist  in the data base", exception.getMessage());
        }
    }

    @Nested
    class findAllClients {

        @Test
        @DisplayName("Should get a Page of Clients With Success")
        void shouldGetAllClientsWithSuccess() {
            //Arrange
            List<Client>clientList= List.of(client,client2);
            Page<Client>pageOfClients=new PageImpl<>(clientList);
            doReturn(pageOfClients)
                    .when(clientRepository)
                    .findAll(pageRequestArgumentCaptor.capture());
            //Act
            var outPut = clientService.findAll(0,2,"ASC");

            //Assert
            assertTrue(outPut.hasBody());
            assertEquals(2, outPut.getBody().size());
        }
    }

    @Nested
    class updateClient {
        @Test
        @DisplayName("Should update a client with  success")
        void shouldUpdateAClientWithSuccess() {
            //Arrange
            // falta definir os novos campos para update
            doReturn(Optional.of(commomUser)).when(userRepository).findById(uuidArgumentCaptor.capture());
            doReturn(Optional.of(client)).when(clientRepository).findById(idArgumentCaptor.capture());
            doReturn(client).when(clientRepository).save(clientArgumentCaptor.capture());
            //Act
            var output = clientService.update(clientDto,token);
            //Assert
            assertNotNull(output.getClientId());
            assertEquals(clientDto.getClientId(),output.getClientId());
            assertEquals(clientDto.getName(),output.getName());
            assertEquals(clientDto.getPhone(),output.getPhone());
            assertEquals(clientDto.getBirthday(),output.getBirthday());
            assertEquals(clientDto.getZipCode(),output.getZipCode());
            assertEquals(clientDto.getUser(),output.getUser());



        }
        @Test
        @DisplayName("Should throws a ResourceNotFoundException when Optional is empty")
        void shouldThrowsAResourceNotFoundExceptionWhenClientNotExists() {
            //Arrange
            doReturn(Optional.of(commomUser)).when(userRepository).findById(uuidArgumentCaptor.capture());
            doReturn(Optional.empty()).when(clientRepository).findById(idArgumentCaptor.capture());

            //Act & Assert
            assertThrows(ResourceNotFoundException.class,()-> clientService.update(clientDto,token));
        }
        @Test
        @DisplayName("Should throws a NotFoundException when Optional is empty")
        void shouldThrowsAForbiddenAccessExceptionWhenClientNotExists() {
            //Arrange
           doReturn(Optional.of(client)).when(clientRepository).findById(idArgumentCaptor.capture());
           doReturn(Optional.of(commomUser)).when(userRepository).findById(uuidArgumentCaptor.capture());
            doThrow(new ForbiddenAccesException("")).when(clientRepository).save(clientArgumentCaptor.capture());
            //Act & Assert
            assertThrows(ForbiddenAccesException.class,()-> clientService.update(clientDto,token));
        }



    }

    @Nested
    class deleteById {
        @Test
        @DisplayName("Should delete a client by id with success")
        void shouldDeleteClientById() {
            //Arrange
            doReturn(Optional.of(commomUser)).when(userRepository).findById(uuidArgumentCaptor.capture());
            doReturn(Optional.of(client)).when(clientRepository).findById(idArgumentCaptor.capture());
            //Act
            clientService.delete(1L, token);
            //Assert
            verify(clientRepository).deleteById(1L);
        }
        @Test
        @DisplayName("Should throws a ResourceNotFoundException when optional is empty")
        void shouldThrowsAExceptionWhenOptionalIsEmptyDeleteClientById() {
            //Arrange
            var id=1L;
            doReturn(Optional.of(commomUser)).when(userRepository).findById(uuidArgumentCaptor.capture());
            doReturn(Optional.empty()).when(clientRepository).findById(idArgumentCaptor.capture());
            //Act & Assert
            assertThrows(ResourceNotFoundException.class,()-> clientService.delete(id,token));
        }
        @Test
        @DisplayName("Should throws a ResourceNotFoundException when optional is empty")
        void shouldThrowsAExceptionWhenUserNotIsAdmin() {
            //Arrange
            var id=1L;

            doReturn(Optional.of(commomUser)).when(userRepository).findById(uuidArgumentCaptor.capture());
            doReturn(Optional.of(client)).when(clientRepository).findById(idArgumentCaptor.capture());

                assertThrows(ForbiddenAccesException.class,()-> clientService.delete(id,token));

            //Act & Assert

        }


    }
}
