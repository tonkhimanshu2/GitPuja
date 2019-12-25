package JiraPck;
import static io.restassured.RestAssured.given;
import io.restassured.RestAssured;
import io.restassured.config.SessionConfig;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import PropertiesPackage.*;

import java.util.Properties;

import org.testng.annotations.Test;

import PropertiesPackage.ResusableMethods;


public class CreateIssueJira { 
	

	@Test
	public void JiraAPI()
	{
		RestAssured.baseURI="http://localhost:8080"; 
	Response rs=	given().header("Content-Type", "application/json"). 
		header("Cookie", "JSESSIONID="+ResusableMethods.SessionCreation()).
		body("{"+
		     "\"fields\": {"+ 
		     "\"project\":{" + 
		          "\"key\": \"RES1\" " +
		        "},"+ 
		        "\"summary\": \"Puja himsCreditCard Issue.\","+
		        "\"description\": \"Blocked and lost card'\","+
		       "\"issuetype\": {"+
		          "\"name\": \"Bug\"" +
		       "}" +
		   
		"}}").when().post("/rest/api/2/issue")
		.then().assertThat().statusCode(201).extract().response();
		
		
		JsonPath js=ResusableMethods.rawToJson(rs); 
		
		System.out.println(ResusableMethods.SessionCreation()); 
		String s=js.get("id");
		System.out.println(s);  
		
	       
		}
	
	

}
