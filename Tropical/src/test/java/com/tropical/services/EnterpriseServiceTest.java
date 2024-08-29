package com.tropical.services;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.tropical.data.dto.EnterpriseDto;
import com.tropical.exceptions.ForbiddenAccesException;
import com.tropical.model.Enterprise;
import com.tropical.model.Role;
import com.tropical.model.TravelPackage;
import com.tropical.model.User;
import com.tropical.repository.EnterpriseRepository;
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

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
class EnterpriseServiceTest {


    @Mock
    private EnterpriseRepository enterpriseRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private JwtAuthenticationToken token;
    @InjectMocks
    private EnterpriseService enterpriseService;

    @Captor
    ArgumentCaptor<Long> idArgumentCaptor;
    @Captor
    ArgumentCaptor<Enterprise> enterpriseArgumentCaptor;
    @Captor
    ArgumentCaptor<UUID> uuidArgumentCaptor;
    @Captor
    ArgumentCaptor<PageRequest> pageRequestArgumentCaptor;
    User user;
    Enterprise enterprise;
    Enterprise enterprise2;
    EnterpriseDto enterpriseDto;
    Role enterpriseRole;
    Role adminRole;
    TravelPackage travelPackage;
    @Captor
    ArgumentCaptor<PageRequest> PageRequestArgumentCaptor;

    @BeforeEach
    void setup() {
        enterpriseRole = new Role();
        enterpriseRole.setName(Role.Values.EMPRESA.name());
        enterpriseRole.setRoleId(Role.Values.EMPRESA.getRoleId());

        adminRole = new Role();
        adminRole.setName(Role.Values.ADMIN.name());
        adminRole.setRoleId(Role.Values.ADMIN.getRoleId());

        user = new User();
        user.setUserId(UUID.randomUUID());
        user.setEmail("enterprise");
        user.setPassword("123");
        user.setRoles(Set.of(enterpriseRole));

        travelPackage = new TravelPackage();


        enterprise = new Enterprise(1L, "JAVA", "2983139491234", "Address", user, List.of(travelPackage));
        enterprise2 = new Enterprise(1L, "JAVA2", "123", "Address2", user, List.of(travelPackage));
        lenient().when(token.getName()).thenReturn(user.getUserId().toString());
    }

    @Nested
    class findEnterprise {
        @Test
        void shouldGetAEmpresaByIdWithSuccess() {
            //Arrange
            doReturn(Optional.of(enterprise)).when(enterpriseRepository).findById(idArgumentCaptor.capture());
            //Act
            var outPut = enterpriseService.findById(1L);
            //Assert
            //verify(enterpriseService,times(1)).findById(1L);
            assertNotNull(outPut);
            assertEquals(1L, outPut.getBody().getEnterpriseId());
            assertEquals(enterprise.getName(), outPut.getBody().getName());
            assertEquals(enterprise.getCnpj(), outPut.getBody().getCnpj());
            assertEquals(enterprise.getAddress(), outPut.getBody().getAddress());
            assertEquals(enterprise.getUser(), outPut.getBody().getUser());
            assertEquals(enterprise.getTravelPackage(), outPut.getBody().getTravelPackage());

        }
    }

    @Nested
    class findAll {

        @Test
        void shouldGetAPageOfCompaniesWithSuccess() {
            var enterpriseList = List.of(enterprise, enterprise2);
            var enterprisePage = new PageImpl<>(enterpriseList);
            //Arrange
            doReturn(enterprisePage).when(enterpriseRepository).findAll(pageRequestArgumentCaptor.capture());
            //Act
            var outPut = enterpriseService.findAll(0, 2, "ASC");

            //Assert
            assertNotNull(outPut);
            assertEquals(enterprisePage.getSize(), outPut.getTotalElements());
        }
    }

    @Nested
    class updateEnterprise {

        @Test
        @DisplayName("Should update when user is admin")
        void shouldUpdateWhenUserIsAdmin() {
            //Arrange
            enterpriseDto = new EnterpriseDto();
            enterpriseDto.setEnterpriseId(1L);
            user.setRoles(Set.of(adminRole));
            enterpriseDto.setUser(user);
            enterpriseDto.setName("New name");
            enterpriseDto.setAddress("New address");
            enterpriseDto.setCnpj("23123412");

            doReturn(Optional.of(user)).when(userRepository).findById(uuidArgumentCaptor.capture());
            doReturn(Optional.of(enterprise)).when(enterpriseRepository).findById(idArgumentCaptor.capture());
            doReturn(enterprise).when(enterpriseRepository).save(enterpriseArgumentCaptor.capture());
            //Act
            var outPut = enterpriseService.update(enterpriseDto, token);
            //Assert
            verify(enterpriseRepository, times(1)).save(enterprise);
            assertEquals(enterpriseDto.getName(), outPut.getName());
            assertEquals(enterpriseDto.getAddress(), outPut.getAddress());
            assertEquals(enterpriseDto.getCnpj(), outPut.getCnpj());
        }

        @Test
        @DisplayName("should throws a Exception when user not have authorization")
        void shouldThrowsAExceptionWhenUserNotHaveAuthorization() {
            //Arrange
            enterpriseDto = new EnterpriseDto();
            enterpriseDto.setEnterpriseId(1L);

            user.setRoles(Set.of(enterpriseRole));
            user.setUserId(UUID.randomUUID());

            enterpriseDto.setUser(user);
            enterpriseDto.setName("New name");
            enterpriseDto.setAddress("New address");
            enterpriseDto.setCnpj("23123412");

            doReturn(Optional.of(user)).when(userRepository).findById(uuidArgumentCaptor.capture());
            doReturn(Optional.of(enterprise)).when(enterpriseRepository).findById(idArgumentCaptor.capture());
            //ACT & Assert
            assertThrows(ForbiddenAccesException.class, () -> enterpriseService.update(enterpriseDto, token));
        }

        @Test
        @DisplayName("Should throw an exception when the enterprise user is not the same as the token user")
        void shouldThrowsAExceptionWhenUserDoesNotEquals() {
            // Arrange
            enterpriseDto = new EnterpriseDto();
            enterpriseDto.setEnterpriseId(1L);
            user.setRoles(Set.of(enterpriseRole));
            user.setUserId(UUID.randomUUID());
            enterpriseDto.setUser(user);
            enterpriseDto.setName("New name");
            enterpriseDto.setAddress("New address");
            enterpriseDto.setCnpj("23123412");

            doReturn(Optional.of(user)).when(userRepository).findById(uuidArgumentCaptor.capture());
            doReturn(Optional.of(enterprise)).when(enterpriseRepository).findById(idArgumentCaptor.capture());
            System.out.println(enterpriseDto.getUser().getUserId().equals(UUID.fromString(token.getName())));
            //Act & Assert
            assertThrows(ForbiddenAccesException.class, () -> enterpriseService.update(enterpriseDto, token));
        }
    }

    @Nested
    class deleteById {
        @Test
        @DisplayName("Should delete a company by id with authentication token when user s Admin")
        void shouldDeleteACompaByIdWithSuccessWhenUserIsAdmin() {
            //arrange
            user.setRoles(Set.of(adminRole));

            doReturn(Optional.of(user)).when(userRepository).findById(uuidArgumentCaptor.capture());
            doReturn(Optional.of(enterprise)).when(enterpriseRepository).findById(idArgumentCaptor.capture());
            //Act
            var outPut = enterpriseService.delete(1L, token);
            //Assert
            verify(enterpriseRepository, times(1)).deleteById(1L);
        }

        @Test
        @DisplayName("Should  Throw a ForbiddenAccesException When User not is admin")
        void shouldNotDeleteAEnterpriseWhenUserNotIsAdmin() {
            //arrange

            user.setRoles(Set.of(enterpriseRole));
            user.setUserId(UUID.randomUUID());

            doReturn(Optional.of(user)).when(userRepository).findById(uuidArgumentCaptor.capture());
            doReturn(Optional.of(enterprise)).when(enterpriseRepository).findById(idArgumentCaptor.capture());
            //Act & Assert
            ForbiddenAccesException exception=assertThrows(ForbiddenAccesException.class,()-> enterpriseService.delete(1L,token));
            verify(enterpriseRepository,never()).deleteById(1L);
            assertEquals("The user :" + user.getEmail() + " does not have permission to perform this operation ",exception.getMessage());



        }


    }
}

