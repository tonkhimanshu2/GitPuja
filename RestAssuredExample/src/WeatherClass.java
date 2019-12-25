import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

public class WeatherClass { 
	
	@Test
	public void GetWeather()
	{
		
		RestAssured.baseURI= "http://restapi.demoqa.com/utilities/weather/city/Hyderabad";
		RequestSpecification httpRequest= RestAssured.given();
		Response res=httpRequest.get("//Hyderabad"); 
		int statusCode = res.getStatusCode();
		
		ResponseBody s= res.getBody();
		//System.out.println (s);
		System.out.println("Response body is:" + res.asString());
		
	}
	

}
