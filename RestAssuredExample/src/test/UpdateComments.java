package test;
import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import PropertiesPackage.ResusableMethods;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class UpdateComments { 
	
	
	@Test
	public void JiraAPI()
	{
		RestAssured.baseURI="http://localhost:8080"; 
	Response rs=	given().header("Content-Type", "application/json"). 
		header("Cookie", "JSESSIONID="+ResusableMethods.SessionCreation()).
		body("{" +
				"\"body\" : \"Update comments by Automations tool and Put Request\","+
				"\"visibilty\" : {"+
					"\"type\" : \"role\","+
					"\"value\" : \"Administrator\""+
				"}"+
			"}").when().put("rest/api/2/issue/10201/comment/10101")
		.then().assertThat().statusCode(200).extract().response();
		
		
		JsonPath js=ResusableMethods.rawToJson(rs); 
		
		System.out.println(ResusableMethods.SessionCreation()); 
		String s=js.get("id");
		System.out.println(s);  
		
	       
		}
	
	

}


