package PropertiesPackage;

import static io.restassured.RestAssured.given;

import org.testng.annotations.BeforeTest;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

public class ResusableMethods { 
	
	
	public static XmlPath rawToXml(Response r)
	{ 
		
		
	
		String str=r.asString(); 
		XmlPath x=new XmlPath(str);
		return x;
		
	} 
	
	
	
	
	
	public static JsonPath rawToJson(Response r)
	{
		String str=r.asString(); 
		JsonPath x1=new JsonPath(str);
		return x1;
		
	}  
	

	public static String SessionCreation()
	{ 
		RestAssured.baseURI="http://localhost:8080";
		Response res= given().header("content-type", "application/json").
		body("{"+ 
				"\"username\": \"pujavrma.11\","+ 
				"\"password\": \"London@123\" " +
				"}").when()
		        .post("/rest/auth/1/session").then().assertThat().statusCode(200).extract().response();
		         	JsonPath x= ResusableMethods.rawToJson(res);
		            String sessionId= x.get("session.value"); 
		            return sessionId; 
		        
		
	}

}
