package com.tropical.services;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.tropical.data.dto.TravelPackageDto;
import com.tropical.exceptions.ForbiddenAccesException;
import com.tropical.exceptions.ResourceNotFoundException;
import com.tropical.model.*;
import com.tropical.repository.EnterpriseRepository;
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
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
class TravelPackageServiceTest {
    @Mock
    TravelPackageRepository travelPackageRepository;
    @Mock
    UserRepository userRepository;
    @Mock
    EnterpriseRepository enterpriseRepository;
    @Mock
    private JwtAuthenticationToken token;
    @InjectMocks
    TravelPackageService travelPackageService;
    @Captor
    ArgumentCaptor<PageRequest> pageRequestArgumentCaptor;
    @Captor
    ArgumentCaptor<UUID> uuidArgumentCaptor;
    @Captor
    ArgumentCaptor<Long> idArgumentCaptor;
    @Captor
    ArgumentCaptor<String> stringArgumentCaptor;
    @Captor
    ArgumentCaptor<TravelPackage> travelPackageArgumentCaptor;

    private TravelPackage travelPackage;
    private TravelPackage travelPackage2;
    private TravelPackageDto travelPackageDto;
    private Enterprise enterprise;
    private Reserve reserve;
    private User user;
    private Role adminRole;
    private Role basicRole;


    @BeforeEach
    void setUp() {
        adminRole = new Role();
        adminRole.setRoleId(Role.Values.BASIC.getRoleId());
        adminRole.setName(Role.Values.ADMIN.name());

        user = new User();
        user.setUserId(UUID.randomUUID());


        reserve = new Reserve();

        enterprise = new Enterprise();
        enterprise.setEnterpriseId(1L);
        enterprise.setName("Gol");

        travelPackage = new TravelPackage(1L, "EUA", "description", "internacional", 3, "image", BigDecimal.valueOf(1000), enterprise, List.of(reserve));
        travelPackage2 = new TravelPackage(2L, "Argentina", "description", "internacional", 4, "image", BigDecimal.valueOf(500), enterprise, List.of(reserve));
        travelPackage2.setId(1L);

        travelPackageDto = new TravelPackageDto();
        travelPackageDto.setId(1L);
        travelPackageDto.setCategory("internacional");
        travelPackageDto.setDays(3);
        travelPackageDto.setEnterprise(enterprise);

        lenient().when(token.getName()).thenReturn(user.getUserId().toString());
    }

    @Nested
    class findById {
        @Test
        @DisplayName("Should get a reserve with success")
        void shouldReturnsAReserveWithSuccess() {
            //Arrange
            doReturn(Optional.of(travelPackage)).when(travelPackageRepository).findById(idArgumentCaptor.capture());
            //Act
            var outPut=travelPackageService.findById(1L,token);
            //Assert
            assertNotNull(outPut);
            assertEquals(travelPackage.getId(),outPut.getId());
            assertEquals(travelPackage.getDays(),outPut.getDays());
            assertEquals(travelPackage.getDestiny(),outPut.getDestiny());

        }

        @Test
        @DisplayName("Should throw  a exception when  optional is empty")
        void shouldThrowsAExceptionWhenOptionalIsEmpty() {
            //Arrange
            doReturn(Optional.empty()).when(travelPackageRepository).findById(idArgumentCaptor.capture());

            //Act & Assert
             assertThrows(ResourceNotFoundException.class,()->travelPackageService.findById(1L,token));
        }
    }


    @Nested
    class findAll {
        @Test
        @DisplayName("Should returns a page of reserves with success")
        void shouldReturnsAPageOfReservesWithSuccess() {
            //Arrange

            var travelPackageList = List.of(travelPackage, travelPackage2);
            var pageOfTravelPackages = new PageImpl<>(travelPackageList);

            doReturn(pageOfTravelPackages).when(travelPackageRepository).findAll(pageRequestArgumentCaptor.capture());
            //Act
            var outPut = travelPackageService.findAll(0, 2, "ASC");
            //Assert
            assertNotNull(outPut);
            assertEquals(pageOfTravelPackages.getSize(), outPut.getSize());
            assertEquals(2, outPut.getTotalElements());

        }
    }


    @Nested
    class create {
        @Test
        @DisplayName("Should create a travel package with succes")
        void shouldCreateATravelPackageWithSuccess() {
            //Arrange
            user.setRoles(Set.of(adminRole));
            doReturn(Optional.of(user)).when(userRepository).findById(uuidArgumentCaptor.capture());
            doReturn(Optional.of(enterprise)).when(enterpriseRepository).findByname(stringArgumentCaptor.capture());
            doReturn(travelPackage).when(travelPackageRepository).save(travelPackageArgumentCaptor.capture());
            //Act
            var outPut = travelPackageService.create(travelPackageDto, token);
            //Assert
            assertNotNull(outPut);

            assertEquals(travelPackageDto.getId(), outPut.getId());
            assertEquals(travelPackageDto.getCategory(), outPut.getCategory());
            assertEquals(travelPackageDto.getDays(), outPut.getDays());
        }

        @Test
        @DisplayName("Should throws a ResourceNotFoundException when optional is empty")
        void shouldThrowsAExceptionWhenOptionalIsEmpty() {
            // Arrange
            basicRole = new Role();
            basicRole.setRoleId(Role.Values.BASIC.getRoleId());
            basicRole.setName(Role.Values.BASIC.name());

            user.setRoles(Set.of(basicRole));
            user.setUserId(UUID.randomUUID());
            enterprise.setUser(user);
            doReturn(Optional.of(user)).when(userRepository).findById(uuidArgumentCaptor.capture());
            doReturn(Optional.of(enterprise)).when(enterpriseRepository).findById(idArgumentCaptor.capture());
            //Act & Assert
            assertThrows(ForbiddenAccesException.class, () -> travelPackageService.create(travelPackageDto, token));
        }
    }


    @Nested
    class update {
        @Test
        @DisplayName("Should update a reserve with success when user is admin")
        void shouldUpdateAReserveWithSuccess() {
            //Arrange
            user.setRoles(Set.of(adminRole));
            doReturn(Optional.of(user)).when(userRepository).findById(uuidArgumentCaptor.capture());
            doReturn(Optional.of(travelPackage)).when(travelPackageRepository).findById(idArgumentCaptor.capture());
            doReturn(travelPackage).when(travelPackageRepository).save(travelPackageArgumentCaptor.capture());
            //Act
            var outPut=travelPackageService.update(travelPackageDto,token);
            //Asseert
            assertNotNull(outPut);
            assertEquals(travelPackageDto.getId(),outPut.getId());
            assertEquals(travelPackageDto.getCategory(),outPut.getCategory());
        }

        @Test
        @DisplayName("should Throws a ResourceNotFoundException when  travel package not  exists")
        void shouldThrowsAExceptionWhenTravelPackageIsEmpty() {
            //Arrange
            user.setRoles(Set.of(adminRole));
            doReturn(Optional.of(user)).when(userRepository).findById(uuidArgumentCaptor.capture());
            doReturn( Optional.empty()).when(travelPackageRepository).findById(idArgumentCaptor.capture());
            //Act & Assert
            assertThrows(ResourceNotFoundException.class,()->travelPackageService.update(travelPackageDto,token));
        }
    }

    @Nested
    class delete {
        @Test
        @DisplayName("Should delete a reserve with success")
        void shouldDeleteAReserveById() {
            //Arrange
            user.setRoles(Set.of(adminRole));
            doReturn(Optional.of(user)).when(userRepository).findById(uuidArgumentCaptor.capture());
            doReturn(Optional.of(travelPackage)).when(travelPackageRepository).findById(idArgumentCaptor.capture());
            //Act
            travelPackageService.delete(1L,token);
            //Assert
            verify(travelPackageRepository,times(1)).delete(travelPackage);

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

            doReturn(Optional.of(user)).when(userRepository).findById(uuidArgumentCaptor.capture());
            doReturn(Optional.of(travelPackage)).when(travelPackageRepository).findById(idArgumentCaptor.capture());

            //Act & Assert
            assertThrows(ForbiddenAccesException.class,()->travelPackageService.delete(1L,token));

        }
    }
}