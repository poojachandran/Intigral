package test;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class errorValidation {

	@Test
	public void restAssuredGET_test_method() {
		String url = "https://api.intigral-ott.net/popcorn-api-rs-7.9.17/v1/promotions?apikey=webB2BGDMSTGExy0sVDlZMzNDdUy";

		//To Validate the status code as 403
		given()
		.when()
		.get(url)
		.then()
		.statusCode(403);


		Response response = given().get(url);
		JsonPath jsonPathEvaluator = response.jsonPath();

		//To validate request Id should not be null
		if(jsonPathEvaluator.get("error.requestId").equals(null)) {
			System.out.println("Request Id is null.");
		}
		else {
			System.out.println("Request Id is not null.");
		}

		//To validate code="8001"
		if(jsonPathEvaluator.get("error.code").equals("8001")) {
			System.out.println("Result success! The code is 8001");
		}
		else {
			System.out.println("Result Failed! The code is "+ jsonPathEvaluator.get("error.code"));
		}

		//To validate message="invalid api key"
		if(jsonPathEvaluator.get("error.message").equals("invalid api key")) {
			System.out.println("Result success! The message is invalid api key.");
		}
		else {
			System.out.println("Result Failed! The message is "+ jsonPathEvaluator.get("error.message"));
		}

	}
}
