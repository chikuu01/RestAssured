package rest_assured_refrence;
import static io.restassured.RestAssured.given;

import java.time.LocalDateTime;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import io.restassured.RestAssured;

public class Rest_patch_refrence {
	@Test
	public static void main(String[] args) {
		//declare the base URL
		RestAssured.baseURI="https://reqres.in/";
		//Declare RequestBody String Variables;
		String requestBody = "{\r\n"
				+ "    \"name\": \"Rishabh\",\r\n"
				+ "    \"job\": \"Manager\"\r\n"
				+ "}";
		JsonPath Jsprequest =new JsonPath(requestBody);
		String Req_Name = Jsprequest.getString("name");
		String Req_Job = Jsprequest.getString("job");
		LocalDateTime currentdate = LocalDateTime.now();
		String expecteddate=currentdate.toString().substring(0, 11);
		//Declare the given when and then methods;
		 String responseBody = given().header("Content-Type","application/json").body(requestBody)
		 .when().patch("api/users/2").then().extract().response().asString();
		//System.out.println(responseBody);
		 //create an object to JSON body to parse the response;
		JsonPath Jspresponse=new JsonPath(responseBody);
		String Res_Name = Jspresponse.getString("name");
		String Res_Job = Jspresponse.getString("job");
		String Res_updatedAt = Jspresponse.getString("updatedAt");
		Res_updatedAt = Res_updatedAt.substring(0,11);
		//Validate the response body parameters;
		Assert.assertEquals(Res_Name,Req_Name);
		Assert.assertEquals(Res_Job,Req_Job);
		Assert.assertEquals(Res_updatedAt,expecteddate);
		//PRINT THE RESULT;
		System.out.println(Req_Name);
		System.out.println(Req_Job);
		System.out.println(expecteddate);
		

	}

}
