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
// import com.tropical.integrationtests.vo.TokenVO;
// import com.tropical.testcontainers.AbstractIntegrationTest;

// import io.restassured.builder.RequestSpecBuilder;
// import io.restassured.filter.log.LogDetail;
// import io.restassured.filter.log.RequestLoggingFilter;
// import io.restassured.filter.log.ResponseLoggingFilter;
// import io.restassured.specification.RequestSpecification;

// @SpringBootTest(webEnvironment=SpringBootTest.WebEnvironment.DEFINED_PORT)
// @TestMethodOrder(OrderAnnotation.class)
// public class ClientControllerJsonTest  extends AbstractIntegrationTest{

	
// 	private static RequestSpecification specification;
// 	private static ObjectMapper objectMapper;
	
// 	private static ClienteVO cliente;
	
// 	@BeforeAll
// 	public static void setup() {
// 		objectMapper = new ObjectMapper();
// 		objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		
// 		cliente = new ClienteVO();
// 	}
	
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
// 				.setBasePath("/api/clientes/v1")
// 				.setPort(TestConfigs.SERVER_PORT)
// 					.addFilter(new RequestLoggingFilter(LogDetail.ALL))
// 					.addFilter(new ResponseLoggingFilter(LogDetail.ALL))
// 				.build();
// }
// 	@Test
// 	@Order(1)
// 	public void testCreate() throws JsonMappingException, JsonProcessingException {
// mockClient();
		
// var content = given().spec(specification)
// 	.contentType(TestConfigs.CONTENT_TYPE_JSON)
// 	.header(TestConfigs.HEADER_PARAM_ORIGIN, TestConfigs.ORIGIN_ERUDIO)
// 		.body(cliente)
// 		.when()
// 		.post()
// 	.then()
// 		.statusCode(200)
// 			.extract()
// 			.body()
// 				.asString();

// ClienteVO persistedClient = objectMapper.readValue(content, ClienteVO.class);
// cliente = persistedClient;
	
// 	assertNotNull(persistedClient);
	
	
	
// 	assertNotNull(persistedClient.getId());
// 	assertNotNull(persistedClient.getCep());
// 	assertNotNull(persistedClient.getEmail());
// 	assertNotNull(persistedClient.getNome());
// 	assertNotNull(persistedClient.getSenha());
// 	assertNotNull(persistedClient.getTelefone());
	
// 	assertTrue(persistedClient.getId() > 0);
	
	
// 	assertEquals("Teste Cep",persistedClient.getCep());
// 	assertEquals("Teste Email",persistedClient.getEmail());
// 	assertEquals("Teste Nome",persistedClient.getNome());
// 	assertEquals("Teste senha",persistedClient.getSenha());
// 	assertEquals("Teste telefone",persistedClient.getTelefone());
// 	}
// 	@Test
// 	@Order(2)
// 	public void testCreatewithWrongOrigin() throws JsonMappingException, JsonProcessingException {
// 		mockClient();
		
// 		var content = given().spec(specification)
// 				.contentType(TestConfigs.CONTENT_TYPE_JSON)
// 				.header(TestConfigs.HEADER_PARAM_ORIGIN, TestConfigs.ORIGIN_SEMERU)
// 				.body(cliente)
// 				.when()
// 					.post()
// 				.then()
// 					.statusCode(403)
// 						.extract()
// 							.body()
// 								.asString();

		
			
// 			assertNotNull(content);
// 			assertEquals("Invalid CORS request", content);
			
			
// 	}
// 	@Test
// 	@Order(3)
// 	public void testFindById() throws JsonMappingException, JsonProcessingException {
// 		mockClient();
		
// 		var content = given().spec(specification)
// 			.contentType(TestConfigs.CONTENT_TYPE_JSON)
// 				.header(TestConfigs.HEADER_PARAM_ORIGIN, TestConfigs.ORIGIN_ERUDIO)
// 				.pathParam("id",cliente.getId())
// 				.when()
// 				.get("{id}")
// 			.then()
// 				.statusCode(200)
// 					.extract()
// 					.body()
// 						.asString();

// ClienteVO persistedClient = objectMapper.readValue(content, ClienteVO.class);
// cliente = persistedClient;
	
// 	assertNotNull(persistedClient);
	
	
	
// 	assertNotNull(persistedClient.getId());
// 	assertNotNull(persistedClient.getCep());
// 	assertNotNull(persistedClient.getEmail());
// 	assertNotNull(persistedClient.getNome());
// 	assertNotNull(persistedClient.getSenha());
// 	assertNotNull(persistedClient.getTelefone());
	
// 	assertTrue(persistedClient.getId() > 0);
	
	
// 	assertEquals("Teste Cep",persistedClient.getCep());
// 	assertEquals("Teste Email",persistedClient.getEmail());
// 	assertEquals("Teste Nome",persistedClient.getNome());
// 	assertEquals("Teste senha",persistedClient.getSenha());
// 	assertEquals("Teste telefone",persistedClient.getTelefone());
// 	}
	
// 	@Test
// 	@Order(4)
// 	public void testFindByIdWithWrongOrigin() throws JsonMappingException, JsonProcessingException {
// mockClient();
		
// var content = given().spec(specification)
// .contentType(TestConfigs.CONTENT_TYPE_JSON)
// .header(TestConfigs.HEADER_PARAM_ORIGIN, TestConfigs.ORIGIN_SEMERU)
// 		.pathParam("id",cliente.getId())
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
