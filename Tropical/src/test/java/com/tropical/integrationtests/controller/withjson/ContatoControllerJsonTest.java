// package com.tropical.integrationtests.controller.withjson;

// import static io.restassured.RestAssured.given;
// import static org.junit.Assert.assertNotNull;
// import static org.junit.Assert.assertTrue;
// import static org.junit.jupiter.api.Assertions.assertEquals;


// import org.junit.jupiter.api.BeforeAll;
// import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
// import org.junit.jupiter.api.Order;
// import org.junit.jupiter.api.Test;
// import org.junit.jupiter.api.TestMethodOrder;
// import org.springframework.boot.test.context.SpringBootTest;

// import com.fasterxml.jackson.core.JsonProcessingException;
// import com.fasterxml.jackson.databind.DeserializationFeature;
// import com.fasterxml.jackson.databind.JsonMappingException;
// import com.fasterxml.jackson.databind.ObjectMapper;
// import com.tropical.configs.TestConfigs;
// import com.tropical.integrationtests.vo.AccountCredentialsVO;
// import com.tropical.integrationtests.vo.ClienteVO;
// import com.tropical.integrationtests.vo.ContatoVO;
// import com.tropical.integrationtests.vo.TokenVO;
// import com.tropical.testcontainers.AbstractIntegrationTest;

// import io.restassured.builder.RequestSpecBuilder;
// import io.restassured.filter.log.LogDetail;
// import io.restassured.filter.log.RequestLoggingFilter;
// import io.restassured.filter.log.ResponseLoggingFilter;
// import io.restassured.specification.RequestSpecification;

// @SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
// @TestMethodOrder(OrderAnnotation.class)
// public class ContatoControllerJsonTest extends AbstractIntegrationTest {

// 	private static RequestSpecification specification;
// 	private static ObjectMapper objectMapper;
	
// 	private static ContatoVO contato;
// 	private static ClienteVO cliente;
// 	@BeforeAll
// 	public static void setup() {
// 	objectMapper=new ObjectMapper();
		
// 		objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		
// 		cliente = new ClienteVO();
// 		contato = new ContatoVO();
		
// ;	}
	
// 	@Test
// 	@Order(0)
// 	public void authorization() throws JsonMappingException, JsonProcessingException {
// 		AccountCredentialsVO user= new AccountCredentialsVO("leandro","admin123");
		
// 		var accessToken = given()
// 				.basePath("/auth/signin")
// 					.port(TestConfigs.SERVER_PORT)
// 					.contentType(TestConfigs.CONTENT_TYPE_JSON)
// 				.body(user)
// 					.when()
// 				.post()
// 					.then()
// 						.statusCode(200)
// 							.extract()
// 							.body()
// 								.as(TokenVO.class)
// 							.getAccessToken();
		
// 		specification = new RequestSpecBuilder()
// 				.addHeader(TestConfigs.HEADER_PARAM_AUTHORIZATION, "Bearer "+accessToken)
// 				.setBasePath("/api/contatos/v1")
// 				.setPort(TestConfigs.SERVER_PORT)
// 					.addFilter(new RequestLoggingFilter(LogDetail.ALL))
// 					.addFilter(new ResponseLoggingFilter(LogDetail.ALL))
// 				.build();
// }
	
// 	@Test
// 	@Order(1)
// 	public void testCreate() throws JsonMappingException, JsonProcessingException {
// 		mockClient();
// 		mockContato();

// 	    var content = given().spec(specification)
// 	            .contentType(TestConfigs.CONTENT_TYPE_JSON)
// 	            .body(contato)
// 	            .when()
// 	            .post()
// 	            .then()
// 	            .statusCode(200)
// 	            .extract()
// 	            .body()
// 	            .asString();

	   

// 	    ContatoVO persistedContato = objectMapper.readValue(content, ContatoVO.class);

// 	    assertNotNull(persistedContato);
// 	    assertNotNull(persistedContato.getId());
// 	    assertNotNull(persistedContato.getAssunto());
// 	    assertNotNull(persistedContato.getMensagem());
// 	    assertNotNull(persistedContato.getCliente());

// 	    // Restante das asserções...
// 	}
	
	
// 	@Test
// 	@Order(3)
// 	public void testFindById() throws JsonMappingException, JsonProcessingException {
// 		mockContato();
		
// 		var content = given().spec(specification)
// 			.contentType(TestConfigs.CONTENT_TYPE_JSON)
// 				.header(TestConfigs.HEADER_PARAM_ORIGIN, TestConfigs.ORIGIN_ERUDIO)
// 				.pathParam("id",contato.getId())
// 				.when()
// 				.get("{id}")
// 			.then()
// 				.statusCode(200)
// 					.extract()
// 					.body()
// 						.asString();
		
	    
// 		ContatoVO persistedContato = objectMapper.readValue(content, ContatoVO.class);
// 		contato = persistedContato;
	
// 	assertNotNull(persistedContato);
	
	
	
// 	assertNotNull(persistedContato.getId());
// 	assertNotNull(persistedContato.getAssunto());
// 	assertNotNull(persistedContato.getMensagem());
// 	assertNotNull(persistedContato.getCliente());
	
	
// 	assertTrue(persistedContato.getId() > 0);
	
	
// 	assertEquals("Assunto Test",persistedContato.getAssunto());
// 	assertEquals("Mensagem Test",persistedContato.getMensagem());
// 	assertEquals("Teste Nome",persistedContato.getCliente());
// 	}
	
// 	@Test
// 	@Order(4)
// 	public void testFindByIdWithWrongOrigin() throws JsonMappingException, JsonProcessingException {
// mockContato();
		
// var content = given().spec(specification)
// .contentType(TestConfigs.CONTENT_TYPE_JSON)
// .header(TestConfigs.HEADER_PARAM_ORIGIN, TestConfigs.ORIGIN_SEMERU)
// 		.pathParam("id",contato.getId())
// 		.when()
// 		.get("{id}")
// 	.then()
// 		.statusCode(403)
// 			.extract()
// 			.body()
// 				.asString();

// 	assertNotNull(content);
// 	assertEquals("Invalid CORS request", content);
	
// 	}
	
// 	private void mockContato() {
// 		contato.setAssunto("Assunto Test");
// 		contato.setMensagem("Mensagem Test");
		
// 		contato.setCliente(cliente);
		
		
		
// 	}
// 	private void mockClient() {
// 		cliente.setNome("Teste Nome");
// 		cliente.setCep("Teste Cep");
// 		cliente.setSenha("Teste senha");
// 		cliente.setTelefone("Teste telefone");
// 		//LocalDate date= LocalDate.of(2024, 11, 17);
// 		//cliente.setDataNascimento(date);// removi por conta de um erro e optei por date
// 		cliente.setEmail("Teste Email");
		
// 	}
	
// }
