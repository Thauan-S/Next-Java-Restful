package com.tropical.services;

import com.tropical.data.dto.EmpresaDTO;
import com.tropical.model.*;
import com.tropical.repository.EmpresaRepository;
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
import org.springframework.data.domain.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

import java.math.BigDecimal;
import java.util.*;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class EmpresaServiceTest {




    @Mock
    private EmpresaRepository empresaRepository;
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private EmpresaService empresaService;

    @Captor
    ArgumentCaptor<Long> IdArgumentCaptor;

    @Captor
    ArgumentCaptor<PageRequest> PageRequestArgumentCaptor;
    @BeforeEach
    void setup(){
        //Arrange
        Cliente cliente=new Cliente(1L, "cliente", "telefone",  new Date(2002-11-17),  "cep");
        Role admin=new Role();
        admin.setRoleId(1L);

        Role basic=new Role();
        basic.setRoleId(2L);

        Role empresaRole=new Role();
        empresaRole.setRoleId(1L);

        Set<Role> roles= Set.of(admin,basic,empresaRole);
        User user=new User(UUID.randomUUID(),"username","password",cliente,roles);
        PacoteDeViagem pacoteDeViagem=new PacoteDeViagem(1L, "destino",  "descricao","categoria",  3, "imagem",
                new BigDecimal(3000), new Empresa());
        Empresa empresa=new Empresa(1L,"empresa","123","endereco",user, List.of(pacoteDeViagem));
        Empresa empresa2=new Empresa(2L,"empresa2","1234","endereco2",user, List.of(pacoteDeViagem));
        List<Empresa>empresaList= Arrays.asList(empresa,empresa2);
        Page empresaPage=new PageImpl<>(empresaList, PageRequest.of(0,2),empresaList.size());
        lenient().doReturn(empresaPage).when(empresaRepository).findAll(PageRequestArgumentCaptor.capture());
        lenient().doReturn(Optional.of(empresa)).when(empresaRepository).findById(1L);
        //Devo usar o lenient sempre , pois ele indicará que irei utilizar todos os mocks, independente se eu estiver
        //utilizando em meu teste ou não.
    }
    @Nested
    class findEmpresa{
    @Test
    void shouldGetAEmpresaByIdWithSuccess() {
        //Arrange
        Long id=1L;
        Cliente cliente=new Cliente(1L, "cliente", "telefone",  new Date(2002-11-17),  "cep");
        Role admin=new Role();
        admin.setRoleId(1L);

        Role basic=new Role();
        basic.setRoleId(2L);

        Role empresaRole=new Role();
        empresaRole.setRoleId(1L);

        Set<Role> roles= Set.of(admin,basic,empresaRole);
        User user=new User(UUID.randomUUID(),"username","password",cliente,roles);
        PacoteDeViagem pacoteDeViagem=new PacoteDeViagem(1L, "destino",  "descricao","categoria",  3, "imagem",
                new BigDecimal(3000), new Empresa());
        Empresa empresa=new Empresa(1L,"empresa","123","endereco",user, List.of(pacoteDeViagem));
           doReturn(Optional.of(empresa)).when(empresaRepository).findById(IdArgumentCaptor.capture());
        //Act
        var outPut=empresaService.findById(1L);
        //Assert
        verify(empresaRepository).findById(1L);
        assertNotNull(outPut);
        assertEquals(1L,outPut.getBody().getEmpresaId());
        assertEquals("empresa",outPut.getBody().getNomeEmpresa());
        assertEquals("123",outPut.getBody().getCnpj());
        assertEquals("endereco",outPut.getBody().getEndereco());
        assertEquals(user,outPut.getBody().getUser());
        assertEquals(List.of(pacoteDeViagem),outPut.getBody().getPacoteDeViagem());

    }
    }
    @Nested
    class findAll {

    @Test
    void shouldGetAPageOfCompaniesWithSuccess() {

        //Act
       var outPut=empresaService.findAll(0,2,"ASC");
        var empresa=empresaRepository.findById(1L).get();
        var empresaList=empresaRepository.findAll(PageRequest.of(0, 2, Sort.Direction.valueOf("ASC") ,"nomeEmpresa"));
        //Assert
        assertNotNull(outPut);
        assertEquals(2,outPut.getTotalElements());
        assertEquals(1L,empresa.getEmpresaId());
        assertEquals(outPut.getSize(),empresaList.getTotalElements());
    }
    }

    @Nested
    class updateEmpresa{

    @Test
    void update() {


            var empresaDto=new EmpresaDTO();
            empresaDto.setEmpresaId(1L);
            empresaDto.setCnpj("123");
            empresaDto.setNomeEmpresa("NovoNome");
        ///var jwt= preciso estudar como obter um token para usar em meu método;

        var outPut=empresaService.update(empresaDto,new JwtAuthenticationToken());

            //Act
            //Assert
   }}
//
//    @Test
//    void delete() {
//    }
}