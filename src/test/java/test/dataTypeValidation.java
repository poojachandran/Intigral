package test;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

import org.testng.annotations.Test;

public class dataTypeValidation {

	@Test
	public void restAssuredGET_test_method() {

		given()
		.when()
		.get("https://api.intigral-ott.net/popcorn-api-rs-7.9.17/v1/promotions?apikey=webB2BGDMSTGExy0sVDlZMzNDdUyZ")
		.then()
		.statusCode(200)
		.assertThat().body(matchesJsonSchemaInClasspath("schema.json"));

	}
}
