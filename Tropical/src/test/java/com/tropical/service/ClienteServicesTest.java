package com.tropical.service;

//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.when;
//
//import java.text.ParseException;
//import java.time.LocalDate;
//import java.util.Optional;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.TestInstance;
//import org.junit.jupiter.api.TestInstance.Lifecycle;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import com.tropical.data.vo.v1.ClienteVO;
//import com.tropical.exceptions.RequiredObjectIsNullException;
//import com.tropical.model.Cliente;
//import com.tropical.repository.ClienteRepository;
//import com.tropical.repository.ContatoRepository;
//import com.tropical.services.ClienteServices;
//import com.tropical.unittests.mapper.mocks.MockCliente;

//@TestInstance(Lifecycle.PER_CLASS)
//@ExtendWith(MockitoExtension.class)
//class ClienteServicesTest {
	
//	MockCliente input;
//	
//	@InjectMocks
//	private ClienteServices service;
//	
//	@Mock
//	ClienteRepository repository;
//	
//	@Mock
//	ContatoRepository contatoRepository;
//	@BeforeEach
//	void setUpMocks() throws Exception {
//		input= new MockCliente();
//		MockitoAnnotations.openMocks(this);
//	}
//
//	@Test
//	void testFindById() throws ParseException {
//		Cliente entity=input.mockEntity(1);
//		entity.setId(1L);
//		when(repository.findById(1L)).thenReturn(Optional.of(entity));
//		
//		var result=service.findById(1L);
//		
//		assertNotNull(result);
//		assertNotNull(result.getKey());
//		assertNotNull(result.getLinks());
//		
//		System.out.println(result.toString());
//		assertTrue(result.toString().contains("links: [</api/clientes/v1/1>;rel=\"self\"]"));
//		assertEquals("Name Test1",result.getNome());
//		assertEquals("Email Test1",result.getEmail());
//		assertEquals("Senha Test1",result.getSenha());
//		assertEquals("Telefone Test1",result.getTelefone());
//		assertEquals("Cep Test1", result.getCep());
//		assertEquals(LocalDate.now(),result.getDataNascimento());
//		
//		
//	}
//
//	@Test
//	void testCreate() throws ParseException {
//		
//		
//		Cliente persisted=input.mockEntity(1);
//		persisted.setId(1L);
//		
//		ClienteVO vo= input.mockVO(1);
//		vo.setKey(1L);
//		
//		when(repository.save(any(Cliente.class))).thenReturn(persisted);
//		
//		var result=service.create(vo);
//		
//		assertNotNull(result);
//		assertNotNull(result.getKey());
//		assertNotNull(result.getLinks());
//		
//		
//		assertTrue(result.toString().contains("links: [</api/clientes/v1/1>;rel=\"self\"]"));
//		assertEquals("Name Test1",result.getNome());
//		assertEquals("Email Test1",result.getEmail());
//		assertEquals("Senha Test1",result.getSenha());
//		assertEquals("Telefone Test1",result.getTelefone());
//		assertEquals("Cep Test1", result.getCep());
//		assertEquals(LocalDate.now(),result.getDataNascimento());
//	}
//	@Test
//	void testCreateWithNullPerson() {
//		Exception exception= assertThrows(RequiredObjectIsNullException.class, ()->{
//			service.create(null);
//		} );
//		String expectedMessage="Não é permitido persistir um objeto nulo";
//		String actualMessage=exception.getMessage();
//		assertTrue(actualMessage.contains(expectedMessage));
//		
//	}
//	@Test
//	void testUpdateWithNullPerson() {
//		Exception exception= assertThrows(RequiredObjectIsNullException.class, ()->{
//			service.update(null);
//		} );
//		String expectedMessage="Não é permitido persistir um objeto nulo";
//		String actualMessage=exception.getMessage();
//		
//		assertTrue(actualMessage.contains(expectedMessage));
//		
//	}

//	@Test
//	void testUpdate() throws ParseException {
//		Cliente entity=input.mockEntity(1);
//		entity.setId(1L);
//		
//		Cliente persisted=entity;
//		persisted.setId(1L);
//		
//		ClienteVO vo= input.mockVO(1);
//		vo.setKey(1L);
//		
//		when(repository.findById(1L)).thenReturn(Optional.of(entity));
//		when(repository.save(entity)).thenReturn(persisted);
//		
//		var result=service.update(vo);
//		
//		assertNotNull(result);
//		assertNotNull(result.getKey());
//		assertNotNull(result.getLinks());
//		
//		
//		assertTrue(result.toString().contains("links: [</api/clientes/v1/1>;rel=\"self\"]"));
//		assertEquals("Name Test1",result.getNome());
//		assertEquals("Email Test1",result.getEmail());
//		assertEquals("Senha Test1",result.getSenha());
//		assertEquals("Telefone Test1",result.getTelefone());
//		assertEquals("Cep Test1", result.getCep());
//		assertEquals(LocalDate.now(),result.getDataNascimento());
//	}

//	@Test
//	void testFindAll() throws ParseException {
//		List<Cliente> list= input.mockEntityList();
//		
//		when(repository.findAll()).thenReturn((list));
//		
//		var clients=service.findAll();
//		assertNotNull(clients);
//		
//		assertEquals(14,clients.size());
//		
//		var personOne=clients.get(1);
//		
//		assertNotNull(personOne);
//		assertNotNull(personOne.getKey());
//		assertNotNull(personOne.getLinks());
//		
//		assertTrue(personOne.toString().contains("links: [</api/clientes/v1/1>;rel=\"self\"]"));
//		assertEquals("Name Test1",personOne.getNome());
//		assertEquals("Email Test1",personOne.getEmail());
//		assertEquals("Senha Test1",personOne.getSenha());
//		assertEquals("Telefone Test1",personOne.getTelefone());
//		assertEquals("Cep Test1", personOne.getCep());
//		assertEquals(LocalDate.now(),personOne.getDataNascimento());
//		
//var personFour=clients.get(4);
//		
//		assertNotNull(personFour);
//		assertNotNull(personFour.getKey());
//		assertNotNull(personFour.getLinks());
//		
//		assertTrue(personFour.toString().contains("links: [</api/clientes/v1/4>;rel=\"self\"]"));
//		assertEquals("Name Test4",personFour.getNome());
//		assertEquals("Email Test4",personFour.getEmail());
//		assertEquals("Senha Test4",personFour.getSenha());
//		assertEquals("Telefone Test4",personFour.getTelefone());
//		assertEquals("Cep Test4", personFour.getCep());
//		assertEquals(LocalDate.now(),personFour.getDataNascimento());
//		
//	}

//	@Test
//	void testDelete() throws ParseException {
//		//rever
//		Cliente entity=input.mockEntity(1);
//		entity.setId(1L);
//		when(repository.findById(1L)).thenReturn(Optional.of(entity));
//		
//		service.delete(1L);
//	}

//}
