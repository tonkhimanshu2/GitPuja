package test;
import static io.restassured.RestAssured.given;



import static org.hamcrest.Matchers.equalTo;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import PropertiesPackage.Resources;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import PropertiesPackage.PayloadBody;

import PropertiesPackage.*;


public class EndToEndScnerio 

{ 
	
	Properties prop= new Properties(); 
	@BeforeTest
	public void getDataProperties() throws IOException
	{
		
		FileInputStream fs=new FileInputStream("C:\\Users\\minky\\eclipse-workspace\\RestAssuredExample\\src\\PropertiesPackage\\Data.Properties");
		prop.load(fs);
		
	} 
	 
	@Test
	public void addAndDelete()
	{
		
		//Response of Post operation
		RestAssured.baseURI = prop.getProperty("HOST");
		
		System.out.println("Heelo i amin BASE URL");
     	Response res=	given().
		queryParam("key", prop.getProperty("KEY")). 
		body(PayloadBody.getPostData()).
		when().
		post(Resources.placePostDataAdd()). 
		then().
		assertThat().statusCode(200).and().contentType(ContentType.JSON).body("status",equalTo("OK")).and(). 
		extract().response(); 
	
	
	// grab the placeId from response: 
		
//	String s=	res.asString();
	//System.out.println(s);  
//	System.out.println("before json parsing");
//	JsonPath js= new JsonPath(s);
//	System.out.println("Parsing done");
     	
     	
     	JsonPath x= ResusableMethods.rawToJson(res);
     	
	String s1=	x.get("place_id");  
	System.out.println(" \n       place Id is : " +s1); 
	
	
	
	//Place this PlaceId in the delete request
	
	
	given().
	queryParam("key", prop.getProperty("KEY")).
	body("{" +
	"\"place_id\" : \""+ s1+" \""+ "}").

	when().
	post(Resources.placePostdatadelete()).
	then().
	assertThat().statusCode(200).and().contentType(ContentType.JSON).body("status",equalTo("OK")).and(). 
	extract().response(); 
	System.out.println("Puja");
	
	
	}

}
