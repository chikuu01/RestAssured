package rest_assured_refrence;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.path.xml.*;

public class soap_Api_refrence {
	@Test
	public static void main(String[] args) {
		//Declare the BaseURL
		RestAssured.baseURI="https://www.dataaccess.com/";
		//Declare RequestBody;
		String RequestBody="<?xml version=\"1.0\" encoding=\"utf-8\"?>\r\n"
				+ "<soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">\r\n"
				+ "  <soap:Body>\r\n"
				+ "    <NumberToWords xmlns=\"http://www.dataaccess.com/webservicesserver/\">\r\n"
				+ "      <ubiNum>7972585665</ubiNum>\r\n"
				+ "    </NumberToWords>\r\n"
				+ "  </soap:Body>\r\n"
				+ "</soap:Envelope>";
		//Extract ResponseBody;
		String ResponseBody=given().header("Content-Type","text/xml; charset=utf-8").body(RequestBody).when().
				post("webservicesserver/NumberConversion.wso").then().extract().response().asString();
		System.out.println(ResponseBody);
		//Parse the ResponseBody
		XmlPath XmlResponse= new XmlPath(ResponseBody);
		String Res_Parameter=XmlResponse.getString("NumberToWordsResult");
		System.out.println(Res_Parameter);
		//Validate the Response Body;
		Assert.assertEquals(Res_Parameter,"seven billion nine hundred and seventy two million five "
				+ "hundred and eighty five thousand six hundred and sixty five ");
				
		

	}

}
