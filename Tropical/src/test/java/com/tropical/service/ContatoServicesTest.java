package com.tropical.service;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.when;
//
//import java.text.ParseException;
//import java.time.LocalDate;
//import java.util.List;
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
//import com.tropical.data.vo.v1.ContatoVO;
//import com.tropical.exceptions.RequiredObjectIsNullException;
//import com.tropical.model.Cliente;
//import com.tropical.model.Contato;
//import com.tropical.repository.ClienteRepository;
//import com.tropical.repository.ContatoRepository;
//import com.tropical.services.ContatoServices;
//import com.tropical.unittests.mapper.mocks.MockCliente;
//import com.tropical.unittests.mapper.mocks.MockContato;
//
//@TestInstance(Lifecycle.PER_CLASS)
//@ExtendWith(MockitoExtension.class)
//class ContatoServicesTest {
//	
//	MockContato input;
//	
//	MockCliente inputclient;
//	@InjectMocks
//	private ContatoServices service;
//	
//	@Mock
//	ContatoRepository repository;
//	
//	@Mock
//	ClienteRepository clienteRepository;
//	@BeforeEach
//	void setUpMocks() throws Exception {
//		input= new MockContato();
//		MockitoAnnotations.openMocks(this);
//		inputclient=new MockCliente();
//		
//	}
//
//	@Test
//	void testFindById() throws ParseException   {
//		Contato entity=input.mockEntity(1);
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
//		assertTrue(result.toString().contains("links: [</api/contatos/v1/1>;rel=\"self\"]"));
//		assertEquals("Assunto Test1",result.getAssunto());
//		assertEquals("Mensagem Test1",result.getMensagem());
//		
//		Cliente cliente=new Cliente();
//	        cliente.setNome("Nome Test1");
//	        cliente.setEmail("Email Test1");
//	        cliente.setSenha("Senha Test1");
//	       LocalDate date= LocalDate.of(2024, 11, 17);
//	        cliente.setDataNascimento(date);
//	        cliente.setCep("1231");
//	        cliente.setId(1L);
//	        cliente.setTelefone("1231");
//	      
//	        assertEquals(cliente.getNome(), entity.getCliente().getNome());
//	        assertEquals(cliente.getEmail(), entity.getCliente().getEmail());
//	        assertEquals(cliente.getSenha(), entity.getCliente().getSenha());
//	        assertEquals(cliente.getDataNascimento(), entity.getCliente().getDataNascimento());
//	        assertEquals(cliente.getCep(), entity.getCliente().getCep());
//	        assertEquals(cliente.getId(), entity.getCliente().getId());
//	        assertEquals(cliente.getTelefone(), entity.getCliente().getTelefone());
//		//assertEquals(cliente,result.getCliente());
//		
//		
//		
//	}
//
//	@Test
//	void testCreate() throws ParseException {
//		
//		
//		Contato persisted=input.mockEntity(1);
//		persisted.setId(1L);
//		
//		ContatoVO vo= input.mockVO(1);
//		vo.setKey(1L);
//		
//		when(repository.save(any(Contato.class))).thenReturn(persisted);
//		
//		var result=service.create(vo);
//		
//		assertNotNull(result);
//		assertNotNull(result.getKey());
//		assertNotNull(result.getLinks());
//		
//		
//		assertTrue(result.toString().contains("links: [</api/contatos/v1/1>;rel=\"self\"]"));
//		//assertEquals("links: [</api/contatos/v1/1>;rel=\"self\"]", result.toString());
//		assertEquals("Assunto Test1",result.getAssunto());
//		assertEquals("Mensagem Test1",result.getMensagem());
//		
//		Cliente cliente=new Cliente();
//	        cliente.setNome("Nome Test1");
//	        cliente.setEmail("Email Test1");
//	        cliente.setSenha("Senha Test1");
//	        LocalDate date= LocalDate.of(2024, 11, 17);
//	        cliente.setDataNascimento(date);
//	        cliente.setCep("1231");
//	        cliente.setId(1L);
//	        cliente.setTelefone("1231");
//	    
//	        assertEquals(cliente.getNome(), result.getCliente().getNome());
//	        assertEquals(cliente.getEmail(), result.getCliente().getEmail());
//	        assertEquals(cliente.getSenha(), result.getCliente().getSenha());
//	        assertEquals(cliente.getDataNascimento(), result.getCliente().getDataNascimento());
//	        assertEquals(cliente.getCep(), result.getCliente().getCep());
//	        assertEquals(cliente.getId(), result.getCliente().getKey());
//	        assertEquals(cliente.getTelefone(), result.getCliente().getTelefone());
//		//assertEquals(cliente,result.getCliente());
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
//
//	@Test // refazer o teste
//	void testUpdate() throws ParseException {
//		Contato entity=input.mockEntity(1);
//		entity.setId(1L);
//		
//		Contato persisted=entity;
//		persisted.setId(1L);
//		
//		ContatoVO vo= input.mockVO(1);
//		vo.setKey(1L);
//		
////		Cliente entitycliente=inputclient.mockEntity(1);
////		entitycliente.setId(1L);
////		
////		Cliente persistedcliente=entitycliente;
////		persisted.setId(1L);
////		
////		ClienteVO voclient= inputclient.mockVO(1);
////		voclient.setKey(1L);
////		when(clienteRepository.findById(1L)).thenReturn(Optional.of(entitycliente));
////		when(clienteRepository.save(entitycliente)).thenReturn(persistedcliente);
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
//		assertTrue(result.toString().contains("links: [</api/contatos/v1/1>;rel=\"self\"]"));
//		assertEquals("Assunto Test1",result.getAssunto());
//		assertEquals("Mensagem Test1",result.getMensagem());
//		
//		Cliente cliente=new Cliente();
//	        cliente.setNome("Nome Test1");
//	        cliente.setEmail("Email Test1");
//	        cliente.setSenha("Senha Test1");
//	        LocalDate date= LocalDate.of(2024, 11, 17);
//	        cliente.setDataNascimento(date);
//	        cliente.setCep("1231");
//	        cliente.setId(1L);
//	        cliente.setTelefone("1231");
//	        
//		//assertEquals(cliente,result.getCliente());
//	}
//
//	@Test
//	void testFindAll() throws ParseException {
//		List<Contato> list= input.mockEntityList();
//		
//		when(repository.findAll()).thenReturn((list));
//		
//		var contatos=service.findAll();
//		assertNotNull(contatos);
//		
//		assertEquals(14,contatos.size());
//		
//		var contatoUm=contatos.get(1);
//		
//		assertNotNull(contatoUm);
//		assertNotNull(contatoUm.getKey());
//		assertNotNull(contatoUm.getLinks());
//		
//		assertTrue(contatoUm.toString().contains("links: [</api/contatos/v1/1>;rel=\"self\"]"));
//		assertEquals("Assunto Test1",contatoUm.getAssunto());
//		assertEquals("Mensagem Test1",contatoUm.getMensagem());
//		
//		Cliente cliente=new Cliente();
//        cliente.setNome("Nome Test1");
//        cliente.setEmail("Email Test1");
//        cliente.setSenha("Senha Test1");
//        LocalDate date= LocalDate.of(2024, 11, 17);
//        cliente.setDataNascimento(date);
//        cliente.setCep("1231");
//        cliente.setId(1L);
//        cliente.setTelefone("1231");
//        
//        assertEquals(cliente.getNome(), contatoUm.getCliente().getNome());
//        assertEquals(cliente.getEmail(), contatoUm.getCliente().getEmail());
//        assertEquals(cliente.getSenha(), contatoUm.getCliente().getSenha());
//        assertEquals(cliente.getDataNascimento(), contatoUm.getCliente().getDataNascimento());
//        assertEquals(cliente.getCep(), contatoUm.getCliente().getCep());
//        assertEquals(cliente.getId(), contatoUm.getCliente().getKey());
//        assertEquals(cliente.getTelefone(), contatoUm.getCliente().getTelefone());
//        //assertEquals(cliente,contatoUm.getCliente());
//		
//		
//		
//var contatoQuatro=contatos.get(4);
//		
//		assertNotNull(contatoQuatro);
//		assertNotNull(contatoQuatro.getKey());
//		assertNotNull(contatoQuatro.getLinks());
//		
//		assertTrue(contatoQuatro.toString().contains("links: [</api/contatos/v1/4>;rel=\"self\"]"));
//		assertEquals("Assunto Test4",contatoQuatro.getAssunto());
//		assertEquals("Mensagem Test4",contatoQuatro.getMensagem());
//		
//		Cliente clienteQuatro=new Cliente();
//		clienteQuatro.setNome("Nome Test4");
//		clienteQuatro.setEmail("Email Test4");
//		clienteQuatro.setSenha("Senha Test4");
//		LocalDate dateQ= LocalDate.of(2024, 11, 17);
//        clienteQuatro.setDataNascimento(dateQ);
//        clienteQuatro.setCep("1234");
//        clienteQuatro.setId(1L);
//        clienteQuatro.setTelefone("1234");
//        
//        assertEquals(cliente.getNome(), contatoUm.getCliente().getNome());
//        assertEquals(cliente.getEmail(), contatoUm.getCliente().getEmail());
//        assertEquals(cliente.getSenha(), contatoUm.getCliente().getSenha());
//        assertEquals(cliente.getDataNascimento(), contatoUm.getCliente().getDataNascimento());
//        assertEquals(cliente.getCep(), contatoUm.getCliente().getCep());
//        assertEquals(cliente.getId(), contatoUm.getCliente().getKey());
//        assertEquals(cliente.getTelefone(), contatoUm.getCliente().getTelefone());
//		
//	}
//
//	@Test
//	void testDelete() throws ParseException {
//		//rever
//		Contato entity=input.mockEntity(1);
//		entity.setId(1L);
//		when(repository.findById(1L)).thenReturn(Optional.of(entity));
//		service.delete(1L);
//	}
//
//}
