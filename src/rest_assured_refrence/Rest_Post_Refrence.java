package rest_assured_refrence;
import static io.restassured.RestAssured.given;

import java.time.LocalDateTime;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import io.restassured.RestAssured;

public class Rest_Post_Refrence {
	@Test
	public static void main(String[] args) {
		//Declare the base URL
		RestAssured.baseURI="https://reqres.in/";
		//Declare request body string variable
		String requestBody="{\r\n"
				+ "    \"name\": \"morpheus\",\r\n"
				+ "    \"job\": \"leader\"\r\n"
				+ "}";
		//Declare the expected result
		JsonPath Jsprequest= new JsonPath(requestBody);
		String Req_name = Jsprequest.getString("name");
		String Req_job = Jsprequest.getString("job");
		LocalDateTime currentdate = LocalDateTime.now();
		String expecteddate=currentdate.toString().substring(0, 11);
		//Declare the given when and then methods
		//String responseBody=given().header("Content-Type","application/json").body(reqresBody).when().
				//post("api/users").then().extract().response().asString();
		String responseBody=given().header("Content-Type","application/json").body(requestBody).when().
				post("api/users").then().extract().response().asString();
		//System.out.println(responseBody);
		//create an object of Json to parse the request
		JsonPath Jspresponse = new JsonPath(responseBody);
		String Res_name = Jspresponse.getString("name");
		String Res_job = Jspresponse.getString("job");
		String Res_createdAt = Jspresponse.getString("createdAt");
		Res_createdAt = Res_createdAt.substring(0,11);
		//validate the responsebody parameter
		Assert.assertEquals(Res_name,Req_name);
		Assert.assertEquals(Res_job,Req_job);
		Assert.assertEquals(Res_createdAt,expecteddate);
		System.out.println(Req_name);
		System.out.println(Req_job);
		System.out.println(expecteddate);
	}

}
