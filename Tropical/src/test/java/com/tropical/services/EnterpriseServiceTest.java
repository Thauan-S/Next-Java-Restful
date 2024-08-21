package com.tropical.services;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.tropical.data.dto.EnterpriseDto;
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
    TravelPackage travelPackage;
    @Captor
    ArgumentCaptor<PageRequest> PageRequestArgumentCaptor;

    @BeforeEach
    void setup() {
        enterpriseRole = new Role();

        user = new User();

        travelPackage = new TravelPackage();

        enterprise = new Enterprise(1L, "JAVA", "2983139491234", "Address", user, List.of(travelPackage));
        enterprise2 = new Enterprise(1L, "JAVA2", "123", "Address2", user, List.of(travelPackage));
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
        void update() {
            //Arrange
            enterpriseDto=new EnterpriseDto();
            enterpriseDto.setName("New name");
            enterpriseDto.setAddress("New address");
            enterpriseDto.setCnpj("23123412");

           doReturn(Optional.of(user)).when(userRepository).findById(uuidArgumentCaptor.capture());
           doReturn(Optional.of(enterprise)).when(enterpriseRepository).findById(idArgumentCaptor.capture());
           doReturn(enterprise).when(enterpriseRepository).save(enterpriseArgumentCaptor.capture());
           //Act
             var outPut=enterpriseService.update(enterpriseDto,token);
            //Assert
            verify(enterpriseRepository,times(1)).save(enterprise);
            assertEquals(enterpriseDto.getName(),outPut.getName());
            
        }
    }

    @Nested
    class deleteById {
        @Test
        @DisplayName("Should delete a company by id with authentication token When user Is Admin")
        void shouldDeleteACompaByIdWithSuccessWhenUserIsAdming() {
//            //arrange
//            var admin=new Role();
//            admin.setName("admin");
//            admin.setRoleId(1L);
//            user.setRoles(Set.of(admin));
//            doReturn(Optional.of(user)).when(userRepository).findById(uuidArgumentCaptor.capture());
//            doReturn(Optional.of(enterprise)).when(empresaRepository).findById(IdArgumentCaptor.capture());
//            //Act
//            var empresa =empresaRepository.findById(1L);
//            var outPut=empresaService.delete(1L,token);
//
//            //Assert
//assertEquals(ResponseEntity.noContent().build(),outPut);
//verify(empresaRepository,times(1)).deleteById(1L);
        }

        @Test
        @DisplayName("Should  Throw a ForbiddenAccesException When User not is admin")
        void shouldNotDeleteACompaByIdWithSuccessWhenUserIsAdming() {
//            //arrange
//            var notAdmin=new Role();
//            notAdmin.setName("not is admin");
//            notAdmin.setRoleId(2L);
//            user.setRoles(Set.of(notAdmin));
//
//            doThrow(new ForbiddenAccesException()).when(userRepository).findById(uuidArgumentCaptor.capture());
//            //Act & Assert
//            ForbiddenAccesException exception=assertThrows(ForbiddenAccesException.class,()-> empresaService.delete(1L,token));
//            verify(empresaRepository,never()).deleteById(1L);
//            assertEquals("O usuário não possui permissão para realizar essa operação",exception.getMessage());
//


        }


    }
}

