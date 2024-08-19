package com.tropical.services;

import com.tropical.model.Enterprise;
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
import org.springframework.data.domain.PageRequest;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

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
    ArgumentCaptor<Long> IdArgumentCaptor;
    @Captor
            ArgumentCaptor<UUID>uuidArgumentCaptor;
    User user;
    Enterprise enterprise;

    @Captor
    ArgumentCaptor<PageRequest> PageRequestArgumentCaptor;
    @BeforeEach
    void setup(){
//        //Arrange
//        Client customer =new Client(1L, "cliente", "telefone",  new Date(2002-11-17),  "cep");
//        Role admin=new Role();
//        admin.setRoleId(1L);
//        admin.setName(Role.Values.ADMIN.toString());
//        user=new User();
//        user.setUserId(UUID.randomUUID());
//        user.setUsername("user");
//        user.setRoles(Set.of(admin));
//        token = mock(JwtAuthenticationToken.class);
//        when(token.getName()).thenReturn(user.getUserId().toString());
//        //Role basic=new Role();
//        //basic.setRoleId(2L);
//
//
//        //Role empresaRole=new Role();
//        //empresaRole.setRoleId(1L);
//
////        Set<Role> roles= Set.of(admin,basic,empresaRole);
//        Set<Role> roles= Set.of(admin);
//        User user=new User(UUID.randomUUID(),"username","password", customer,roles);
//        TravelPackage travelPackage =new TravelPackage(1L, "destino",  "descricao","categoria",  3, "imagem",
//                new BigDecimal(3000), new Enterprise());
//         enterprise =new Enterprise(1L,"empresa","123","endereco",user, List.of(travelPackage));
//        Enterprise enterprise2 =new Enterprise(2L,"empresa2","1234","endereco2",user, List.of(travelPackage));
//        List<Enterprise> enterpriseList = Arrays.asList(enterprise, enterprise2);
//        Page empresaPage=new PageImpl<>(enterpriseList, PageRequest.of(0,2), enterpriseList.size());
//        lenient().doReturn(Optional.of(user)).when(userRepository).findById(uuidArgumentCaptor.capture());
//        lenient().doReturn(empresaPage).when(empresaRepository).findAll(PageRequestArgumentCaptor.capture());
//        lenient().doReturn(Optional.of(enterprise)).when(empresaRepository).findById(1L);
//        //Devo usar o lenient sempre , pois ele indicará que irei utilizar todos os mocks, independente se eu estiver
//        //utilizando em meu teste ou não.
    }
    @Nested
    class findEnterprise {
    @Test
    void shouldGetAEmpresaByIdWithSuccess() {
//        //Arrange
//        Long id=1L;
//        Customer customer =new Customer(1L, "cliente", "telefone",  new Date(2002-11-17),  "cep");
//        Role admin=new Role();
//        admin.setRoleId(1L);
//
//        Role basic=new Role();
//        basic.setRoleId(2L);
//
//        Role empresaRole=new Role();
//        empresaRole.setRoleId(1L);
//
//        Set<Role> roles= Set.of(admin,basic,empresaRole);
//        User user=new User(UUID.randomUUID(),"username","password", customer,roles);
//        TravelPackage travelPackage =new TravelPackage(1L, "destino",  "descricao","categoria",  3, "imagem",
//                new BigDecimal(3000), new Enterprise());
//        Enterprise enterprise =new Enterprise(1L,"empresa","123","endereco",user, List.of(travelPackage));
//           doReturn(Optional.of(enterprise)).when(empresaRepository).findById(IdArgumentCaptor.capture());
//        //Act
//        var outPut=empresaService.findById(1L);
//        //Assert
//        verify(empresaRepository).findById(1L);
//        assertNotNull(outPut);
//        assertEquals(1L,outPut.getBody().getEmpresaId());
//        assertEquals("empresa",outPut.getBody().getNomeEmpresa());
//        assertEquals("123",outPut.getBody().getCnpj());
//        assertEquals("endereco",outPut.getBody().getAddress());
//        assertEquals(user,outPut.getBody().getUser());
//        assertEquals(List.of(travelPackage),outPut.getBody().getPacoteDeViagem());

    }
    }
    @Nested
    class findAll {

    @Test
    void shouldGetAPageOfCompaniesWithSuccess() {

//        //Act
//       var outPut=empresaService.findAll(0,2,"ASC");
//        var empresa=empresaRepository.findById(1L).get();
//        var empresaList=empresaRepository.findAll(PageRequest.of(0, 2, Sort.Direction.valueOf("ASC") ,"nomeEmpresa"));
//        //Assert
//        assertNotNull(outPut);
//        assertEquals(2,outPut.getTotalElements());
//        assertEquals(1L,empresa.getEmpresaId());
//        assertEquals(outPut.getSize(),empresaList.getTotalElements());
    }
    }

    @Nested
    class updateEnterprise {

    @Test
    void update() {
//
//
//            var empresaDto=new EnterpriseDto();
//            empresaDto.setEmpresaId(1L);
//            empresaDto.setCnpj("123");
//            empresaDto.setNomeEmpresa("NovoNome");
//        ///var jwt= preciso estudar como obter um token para usar em meu método;
//
//       // var outPut=empresaService.update(empresaDto,new JwtAuthenticationToken());
//
//            //Act
//            //Assert
   }}
    @Nested
    class deleteById{
        @Test
        @DisplayName("Should delete a company by id with authentication token When user Is Admin")
        void shouldDeleteACompaByIdWithSuccessWhenUserIsAdming(){
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
        void shouldNotDeleteACompaByIdWithSuccessWhenUserIsAdming(){
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

