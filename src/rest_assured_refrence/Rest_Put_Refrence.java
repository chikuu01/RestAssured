package rest_assured_refrence;
import static io.restassured.RestAssured.given;

import java.time.LocalDateTime;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import io.restassured.RestAssured;

public class Rest_Put_Refrence {
	@Test
	public static void main(String[] args) {
		//Declare the base URL;
		RestAssured.baseURI="https://reqres.in/";
		//Declare Requestbody string variable;
		String requestBody="{\r\n"
				+ "    \"name\": \"Krushabh\",\r\n"
				+ "    \"job\": \"Leader\"\r\n"
				+ "}";
		JsonPath Jsprequest = new JsonPath(requestBody);
		String Req_Name = Jsprequest.getString("name");
		String Req_Job = Jsprequest.getString("job");
		LocalDateTime currentdate = LocalDateTime.now();
		String expecteddate=currentdate.toString().substring(0, 11);
		//Declare the given when and then methods;
		//String responseBody=given().header("Content-Type","application/json").body(requestBody)
				//.when().put("api/users/2").then().extract().response().asString();
        //System.out.println(responseBody);
		String responseBody=given().header("Content-Type","application/json").body(requestBody)
				.when().put("api/users/2").then().extract().response().asString();
		
		//Create an object to JSON path to parse an response body;
		JsonPath Jspresponse = new JsonPath(responseBody);
		String Res_Name=Jspresponse.getString("name");
		String Res_Job=Jspresponse.getString("job");
		String Res_updatedAt=Jspresponse.getString("updatedAt");
		Res_updatedAt = Res_updatedAt.substring(0,11);
		//Validate the response body parameters
		Assert.assertEquals(Res_Name,Req_Name);
		Assert.assertEquals(Res_Job,Req_Job);
		Assert.assertEquals(Res_updatedAt,expecteddate);
		System.out.println(Req_Name);
		System.out.println(Req_Job);
		System.out.println(expecteddate);
	}

}
