// package com.tropical.testcontainers.swagger;

// import static io.restassured.RestAssured.given;

// import org.junit.jupiter.api.Assertions;
// import org.junit.jupiter.api.Test;
// import org.springframework.boot.test.context.SpringBootTest;

// import com.tropical.configs.TestConfigs;
// import com.tropical.testcontainers.AbstractIntegrationTest;

// @SpringBootTest(webEnvironment=SpringBootTest.WebEnvironment.DEFINED_PORT)
// public class SwaggerIntegrationTest  extends AbstractIntegrationTest{

// 	@Test
// 	public void shouldDisplaySwaggerUiPage() {
// 		var content=
// 		given()	
// 			.basePath("/swagger-ui/index.html")
// 			.port(TestConfigs.SERVER_PORT)
// 			.when()
// 				.get()
// 			.then()
// 				.statusCode(200)
// 			.extract()
// 				.body().asString();
// 		Assertions.assertTrue(content.contains("Swagger UI"));
// 	}

// }
