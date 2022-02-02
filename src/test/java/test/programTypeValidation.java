package test;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

import java.util.ArrayList;

import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class programTypeValidation {

	@Test
	public void restAssuredGET_test_method() {

		//To validate promotion id has string value.
		given()
		.when()
		.get("https://api.intigral-ott.net/popcorn-api-rs-7.9.17/v1/promotions?apikey=webB2BGDMSTGExy0sVDlZMzNDdUyZ")
		.then()
		.statusCode(200)
		.assertThat().body(matchesJsonSchemaInClasspath("schema2.json"));
		

		//To validate Program Type has EPISODE or MOVIE or SERIES or SEASON values

		Response response = given()
		.get("https://api.intigral-ott.net/popcorn-api-rs-7.9.17/v1/promotions?apikey=webB2BGDMSTGExy0sVDlZMzNDdUyZ");
		JsonPath jsonPathEvaluator = response.jsonPath();
		
		ArrayList<Object> programType= new ArrayList<Object>();
		programType = jsonPathEvaluator.get("promotions.properties.programType");
		
		for(int i=0; i<programType.size(); i++) {
			if((programType.get(i).toString()).equalsIgnoreCase("[EPISODE]")||
					(programType.get(i).toString()).equalsIgnoreCase("[MOVIE]")||
					(programType.get(i).toString()).equalsIgnoreCase("[SERIES]")||
					(programType.get(i).toString()).equalsIgnoreCase("[SEASON]")) {
				System.out.println("The result is success! Program Type value is "+programType.get(i).toString());
			}
			else if((programType.get(i).toString()).equalsIgnoreCase("[null]")){
				System.out.println("The result is success! Program Type value is null");
			}
			else {
				System.out.println("Result not success! Program Type does not contain EPISODE or MOVIE or SERIES or SEASON values. ");
			}
		}
	}
}
