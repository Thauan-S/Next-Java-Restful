package com.tropical.services;


import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.tropical.data.dto.ReserveDto;
import com.tropical.exceptions.ForbiddenAccesException;
import com.tropical.exceptions.ResourceNotFoundException;
import com.tropical.model.*;
import com.tropical.repository.ClientRepository;
import com.tropical.repository.ReserveRepository;
import com.tropical.repository.TravelPackageRepository;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ReserveServiceTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private ClientRepository clientRepository;
    @Mock
    private TravelPackageRepository travelPackageRepository;
    @Mock
    private ReserveRepository reserveRepository;
    @InjectMocks
    ReserveService reserveService;
    @Mock
    private JwtAuthenticationToken token;
    private Reserve reserve;
    private Reserve reserve2;
    private Client client;
    private TravelPackage travelPackage;
    private ReserveDto reserveDto;
    private User user;
    private Role adminRole;
    private Role basicRole;
    @Captor
    ArgumentCaptor<Reserve> reserveArgumentCaptor;
    @Captor
    ArgumentCaptor<Long>idArgumentCaptor ;
    @Captor
    ArgumentCaptor<String>stringArgumentCaptor;
    @Captor
    ArgumentCaptor<PageRequest>pageRequestArgumentCaptor;
    @Captor
    ArgumentCaptor<UUID>uuidArgumentCaptor;

    @BeforeEach
    void setUp(){
        travelPackage=new TravelPackage();
        travelPackage.setId(1L);


        adminRole=new Role();
        adminRole.setRoleId(Role.Values.ADMIN.getRoleId());
        adminRole.setName(Role.Values.ADMIN.name());

        user=new User();
        user.setUserId(UUID.randomUUID());
        user.setUsername("thau");
        user.setPassword("123");


        client=new Client();
        client.setUser(user);


        reserve=new Reserve(1L, ZonedDateTime.now(ZoneId.of("America/Sao_Paulo")),new Date(2024-8-22),client,travelPackage);
        reserve2=new Reserve(2L, ZonedDateTime.now(),new Date(2024-8-23),client,travelPackage);

        reserveDto=new ReserveDto(1L,ZonedDateTime.now(ZoneId.of("America/Sao_Paulo")) ,new Date(2024-8-22),client,travelPackage);

        lenient().when(token.getName()).thenReturn(user.getUserId().toString());
    }
    @Nested
    class findById{
        @Test
        @DisplayName("Should get a reserve with success")
        void shouldReturnsAReserveWithSuccess() {
            //Arrange
            doReturn(Optional.of(reserve)).when(reserveRepository).findById(idArgumentCaptor.capture());
            //Act
            var outPut=reserveService.findById(1L);
            //Assert
            assertEquals(reserve.getReserveId(),outPut.getReserveId());
            assertEquals(reserve.getClient(),outPut.getClient());
            assertEquals(reserve.getCreationDate(),outPut.getCreationDate());
            assertEquals(reserve.getTravelDate(),outPut.getTravelDate());
            assertEquals(reserve.getTravelPackage(),outPut.getTravelPackage());
        }
        @Test
        @DisplayName("Should throw  a exception when  optional is empty")
        void shouldThrowsAExceptionWhenOptionalIsEmpty() {
            //Arrange
            doReturn(Optional.empty()).when(reserveRepository).findById(idArgumentCaptor.capture());

            //Act & Assert
            assertThrows(ResourceNotFoundException.class,()->reserveService.findById(1L));
        }
    }

    @Nested
    class findReserveByClientName{
    @Test
    void shouldGetAReserveWithSuccess() {
        //Arrange
        doReturn(List.of(reserve)).when(reserveRepository).findByClient_User_Username(stringArgumentCaptor.capture());
        //Act
        var outPut=reserveService.findReserveByClientName("thauan",token);
        //Assert
        assertNotNull(outPut);
        assertEquals(reserve.getReserveId(),outPut.get(0).getReserveId());
        assertEquals(reserve.getClient().getName(),outPut.get(0).getClient().getName());
        assertEquals(reserve.getCreationDate(),outPut.get(0).getCreationDate());
        verify(reserveRepository,times(1)).findByClient_User_Username("thauan");
    }
    }

    @Nested
    class findAll{
        @Test
        @DisplayName("Should returns a page of reserves with success")
        void shouldReturnsAPageOfReservesWithSuccess() {
            //Arrange
            var reservesList=List.of(reserve,reserve2);
            var pageOfReserves=new PageImpl<>(reservesList);
            doReturn(pageOfReserves).when(reserveRepository).findAll(pageRequestArgumentCaptor.capture());
            //Act
            var outPut=reserveService.findAll(0,2,"ASC");
            //Assert
            assertNotNull(outPut);
            assertEquals(pageOfReserves.getSize(),outPut.getSize());
            assertEquals(2,outPut.getTotalElements());

        }
    }


    @Nested
    class create{
        @Test
        void shouldCreateAReserveWithSuccess() {
        //Arrange

            doReturn(Optional.of(client)).when(clientRepository).findByUser_username(stringArgumentCaptor.capture());
           doReturn(Optional.of(travelPackage)).when(travelPackageRepository).findById(idArgumentCaptor.capture());
           doReturn(reserve).when(reserveRepository).save(reserveArgumentCaptor.capture());
           //Act
            var outPut=reserveService.create(reserveDto,token);
            //Assert
            assertNotNull(outPut);

            assertEquals(reserveDto.getClient(),outPut.getClient());
            assertEquals(reserveDto.getTravelDate(),outPut.getTravelDate());
            assertEquals(reserveDto.getTravelPackage(),outPut.getTravelPackage());
        }
        @Test
        @DisplayName("Should throws a ResourceNotFoundException when optional is empty")
        void shouldThrowsAExceptionWhenOptionalIsEmpty() {
            //Arrange
            doReturn(Optional.empty()).when(clientRepository).findByUser_username(stringArgumentCaptor.capture());
            doReturn(Optional.empty()).when(travelPackageRepository).findById(idArgumentCaptor.capture());
            //Ac & Assert
            assertThrows(ResourceNotFoundException.class,()-> reserveService.create(reserveDto,token));
        }
    }


    @Nested
    class update{
        @Test
        @DisplayName("Should update a reserve with success when user is admin")
        void shouldUpdateAReserveWithSuccess() {
            //Arrange
            user.setRoles(Set.of(adminRole));
            doReturn(Optional.of(user)).when(userRepository).findById(uuidArgumentCaptor.capture());
            doReturn(Optional.of(reserve)).when(reserveRepository).findById(idArgumentCaptor.capture());
            doReturn(Optional.of(travelPackage)).when(travelPackageRepository).findById(idArgumentCaptor.capture());
            doReturn(reserve).when(reserveRepository).save(reserveArgumentCaptor.capture());
            //Act
            var outPut=reserveService.update(reserveDto,token);
            //Asseert
            assertNotNull(outPut);
        }
        @Test
        @DisplayName("should Throws a ResourceNotFoundException when  reserve not  exists")
        void shouldThrowsAExceptionWhenReserveIsEmpty() {
            //Arrange
            doReturn(Optional.of(user)).when(userRepository).findById(uuidArgumentCaptor.capture());
            doReturn(Optional.empty()).when(reserveRepository).findById(idArgumentCaptor.capture());
            //Act & Assert
            assertThrows(ResourceNotFoundException.class,()->reserveService.update(reserveDto,token));


        }
        @Test
        @DisplayName("should Throws a ResourceNotFoundException when  travel package not  exists")
        void shouldThrowsAExceptionWhenTravelPackageIsEmpty() {
            //Arrange
            lenient().doReturn( Optional.empty()).when(travelPackageRepository).findById(idArgumentCaptor.capture());
            //Act & Assert
            assertThrows(ResourceNotFoundException.class,()->reserveService.update(reserveDto,token));


        }
        @Test
        @DisplayName("should Throws a ForbiddenAccesException when  user  not  is Admin")
        void shouldThrowsAExceptionWhenUserNotIsAdmin() {
            //Arrange
            basicRole=new Role();
            basicRole.setRoleId(Role.Values.BASIC.getRoleId());
            basicRole.setName(Role.Values.BASIC.name());

            user.setRoles(Set.of(basicRole));
            user.setUserId(UUID.randomUUID());
            client.setUser(user);
            reserveDto.setClient(client);
            doReturn(Optional.of(user)).when(userRepository).findById(uuidArgumentCaptor.capture());
            doReturn(Optional.of(reserve)).when(reserveRepository).findById(idArgumentCaptor.capture());
            doReturn(Optional.of(travelPackage)).when(travelPackageRepository).findById(idArgumentCaptor.capture());
            //Act & Assert
            assertThrows(ForbiddenAccesException.class,()->reserveService.update(reserveDto,token));


        }
    }

    @Nested
    class delete{
        @Test
        @DisplayName("Should delete a reserve with success")
        void shouldDeleteAReserveById() {
            //Arrange
            user.setRoles(Set.of(adminRole));
            doReturn(Optional.of(user)).when(userRepository).findById(uuidArgumentCaptor.capture());
            doReturn(Optional.of(reserve)).when(reserveRepository).findById(idArgumentCaptor.capture());
            //Act
            reserveService.delete(1L,token);
            //Assert
            verify(reserveRepository,times(1)).deleteById(1L);

        }
        @Test
        @DisplayName("Should throws a ForbiddenAccessException when user not  have permission")
        void shouldThrowsAException() {
            //Arrange
            basicRole=new Role();
            basicRole.setName(Role.Values.BASIC.name());
            basicRole.setRoleId(Role.Values.BASIC.getRoleId());
            user.setRoles(Set.of(basicRole));
            user.setUserId(UUID.randomUUID());
            user.setPassword("123");
            user.setUsername("thau");
            client.setUser(user);
            reserve.setClient(client);

            doReturn(Optional.of(user)).when(userRepository).findById(uuidArgumentCaptor.capture());
            doReturn(Optional.of(reserve)).when(reserveRepository).findById(idArgumentCaptor.capture());
            doThrow(new ForbiddenAccesException("The user: " + user.getUsername() + " does not have permission to perform this operation")).when(reserveRepository).deleteById(idArgumentCaptor.capture());
            //Act & Assert
            assertThrows(ForbiddenAccesException.class,()->reserveService.delete(1L,token));

        }
    }

}