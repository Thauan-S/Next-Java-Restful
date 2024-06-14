package com.tropical.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


import java.util.*;


import com.tropical.controller.ClienteController;
import com.tropical.model.Role;
import com.tropical.model.User;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.tropical.model.Cliente;
import com.tropical.repository.ClienteRepository;
import com.tropical.repository.UserRepository;
import com.tropical.services.ClienteService;
import org.springframework.data.domain.*;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.webjars.NotFoundException;

@ExtendWith(MockitoExtension.class)
public class ClienteServiceTest {
	@Mock
	private ClienteRepository clienteRepository;
	@Mock
	private UserRepository userRepository;
	@InjectMocks
	 private ClienteService clienteService;

	
	@Captor
	private ArgumentCaptor<Cliente> clienteArgumentCaptor;
	@Captor
	private ArgumentCaptor<Long> IdArgumentCaptor;
	@Captor
	private ArgumentCaptor<PageRequest> PageRequestArgumentCaptor;
	@Captor
	private ArgumentCaptor<UUID> UuidArgumentCaptor;
	
	@Nested
	class createClient{
		
		
	
		@Test
		void deveBuscarUmCliente() {
			var cliente= new Cliente(
					1L,
					"Thauan",
					"73988896878",
					new Date(2002-17-11),
					"45330000"
					);
			//Arrange
			//doReturn(cliente).when(clienteRepository).save(clienteArgumentCaptor.capture());
			doReturn(Optional.of(cliente))
					.when(clienteRepository)
					.findById(IdArgumentCaptor.capture());
			//var input = new ClienteDto(1L,"Thauan","73988896878",new Date(2002-17-11),"45330000");
			
			//clienteService.save();
			//Act
			 var outPut=clienteRepository.findById(1L);
			
			//Assert
			//assertNotNull(outPut.getClienteId());
			assertEquals("Thauan", outPut.get().getNome());
			assertEquals(cliente.getNome(),outPut.get().getNome());
		}	
		@Test
		void deveBuscarUmClienteELancarException() {
			//Arrange
			var clientId=1L;
			doReturn(Optional.empty()).when(clienteRepository).findById(IdArgumentCaptor.capture());
			//Act
			 Optional<Cliente>outPut=clienteRepository.findById(1L);

			//Assert
			//assertNotNull(outPut.getClienteId());
			NotFoundException exception=assertThrows(NotFoundException.class,()-> clienteService.findById(clientId));

			assertThrows(NotFoundException.class,()-> clienteService.findById(1L));
			assertEquals("O cliente de id" + clientId + "não existe na base de dados",exception.getMessage());
		}	
	}
	
	@Nested
	class findClient{
		
		@Test
		void shouldGetAClientByIdWithSuccess() {
			//Arrange
			var cliente= new Cliente(
					1L,
					"Thauan",
					"73988896878",
					new Date(2002-17-11),
					"45330000"
					);
			doReturn(Optional.of(cliente))
			.when(clienteRepository)
			.findById(IdArgumentCaptor.capture());
			//Act
			var outPut=clienteRepository.findById(cliente.getClienteId());
		
			//Assert
			assertTrue(outPut.isPresent());
			assertEquals(1L, outPut.get().getClienteId());
			assertEquals("Thauan", outPut.get().getNome());
			assertEquals("73988896878", outPut.get().getTelefone());
			assertEquals(new Date(2002-17-11), outPut.get().getDataNascimento());
			assertEquals("45330000", outPut.get().getCep());
		}
		@Test
		void shouldGetAClientByIdWithSuccessWhenOptionalIsEmpty() {
			//Arrange
			var cliente= new Cliente(
					1L,
					"Thauan",
					"73988896878",
					new Date(2002-17-11),
					"45330000"
					);
			doReturn(Optional.empty())
			.when(clienteRepository)
			.findById(IdArgumentCaptor.capture());
			//Act
			var outPut=clienteRepository.findById(cliente.getClienteId());
		
			//Assert
			assertNotNull(outPut);
			assertTrue(outPut.isEmpty());
		
		}
		
	}

	@Nested
	class listClient{
		@Test
		void shouldReturnAllClientsWithSuccess(){
			//Arrange
		var cliente1= new Cliente(1L, "Thauan", "73988896878", new Date(2002-17-11), "45330000");
			var cliente2= new Cliente(2L, "Thauan2", "73988896877", new Date(2002-17-12), "45330001");
		//Page<Cliente>page=new Page.of(cliente);
		List<Cliente>clientesList= Arrays.asList(cliente1,cliente2);

		 Page<Cliente>clientePage = new PageImpl<>(clientesList, PageRequest.of(0, 2), clientesList.size());
		//doReturn(page.get().toList())
			doReturn(clientePage)
		.when(clienteRepository)
		.findAll(PageRequestArgumentCaptor.capture());
			
		//Act
		var output=clienteService.findAll(1,2,"ASC");
		
		
		//Assert
		assertNotNull(output);
		assertEquals(clientesList.size(), Objects.requireNonNull(output.getBody()).size());
		assertEquals(clientesList.get(0).getClienteId(),cliente1.getClienteId());
		assertEquals(clientesList.get(1).getClienteId(),cliente2.getClienteId());
		assertEquals(clientePage.getSize(),clientesList.size());
		}


	}

	@Nested
	class deleteById{
		@Test
		void shouldDeleteClientById(){
			//Arrange
			Long IdToDelete=1L;
			var cliente=new Cliente(1L, "Thauan", "73988896878", new Date(2002-17-11), "45330000");
			Role admin=new Role();
			admin.setRoleId(1L);
			Role basic=new Role();
			admin.setRoleId(2L);
			Role empresa=new Role();
			admin.setRoleId(3L);
			Set<Role>roles= Set.of(admin,basic,empresa);

		  		var jwt= new JwtAuthenticationToken(null,List.of(new SimpleGrantedAuthority("ROLE_USER")),UUID.randomUUID().toString());

			User user= new  User(
					UUID.randomUUID(),
					"username",
					"password",
					 cliente,
					 roles) ;
			doReturn(Optional.of(user)).when(userRepository).findById(UuidArgumentCaptor.capture());
			doReturn(cliente).when(clienteRepository).findById(IdArgumentCaptor.capture());
			doNothing().when(clienteRepository).deleteById(IdArgumentCaptor.capture());
			//Act
				clienteService.delete(1L,jwt);
			//Assert
		verify(clienteRepository).deleteById(IdToDelete);
		}


	}
}
