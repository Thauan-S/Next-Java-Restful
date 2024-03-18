// package com.tropical.integrationtests.controller.withxml;

// import static io.restassured.RestAssured.given;
// import static org.junit.Assert.assertNotNull;

// import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
// import org.junit.jupiter.api.Order;
// import org.junit.jupiter.api.Test;
// import org.junit.jupiter.api.TestMethodOrder;
// import org.springframework.boot.test.context.SpringBootTest;

// import com.fasterxml.jackson.core.JsonProcessingException;
// import com.fasterxml.jackson.databind.JsonMappingException;
// import com.tropical.configs.TestConfigs;
// import com.tropical.integrationtests.vo.AccountCredentialsVO;
// import com.tropical.integrationtests.vo.TokenVO;
// import com.tropical.testcontainers.AbstractIntegrationTest;

// @SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
// @TestMethodOrder(OrderAnnotation.class)
// public class AuthControllerXmlTest extends AbstractIntegrationTest {
	
// 	private static TokenVO tokenVO;
	
// 	@Test
// 	@Order(1)
// 	public void TestSignin() throws JsonMappingException, JsonProcessingException {
// 		AccountCredentialsVO user= new AccountCredentialsVO("leandro","admin123");
		
// 		tokenVO = given()
// 				.basePath("/auth/signin")
// 					.port(TestConfigs.SERVER_PORT)
// 					.contentType(TestConfigs.CONTENT_TYPE_XML)
// 				.body(user)
// 					.when()
// 				.post()
// 					.then()
// 						.statusCode(200)
// 							.extract()
// 							.body()
// 								.as(TokenVO.class);
							
// 		assertNotNull(tokenVO.getAccessToken());
// 		assertNotNull(tokenVO.getRefreshToken());
// }
// 	@Test
// 	@Order(2)
// 	public void TestRefresh() throws JsonMappingException, JsonProcessingException {
// 		AccountCredentialsVO user= new AccountCredentialsVO("leandro","admin123");
		
// 		var newtokenVO = given()
// 				.basePath("/auth/refresh")
// 					.port(TestConfigs.SERVER_PORT)
// 					.contentType(TestConfigs.CONTENT_TYPE_JSON)
// 						.pathParam("username", tokenVO.getUsername())
// 						.header(TestConfigs.HEADER_PARAM_AUTHORIZATION,"Bearer "+ tokenVO.getRefreshToken())
// 					.when()
// 						.put("{username}")
// 					.then()
// 						.statusCode(200)
// 							.extract()
// 							.body()
// 								.as(TokenVO.class);
							
// 		assertNotNull(newtokenVO.getAccessToken());
// 		assertNotNull(newtokenVO.getRefreshToken());
// }
// }
