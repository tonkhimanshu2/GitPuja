package test;
import io.restassured.RestAssured;

import PropertiesPackage.*;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;

public class WithParameterization {

	@Test
	public void test1()
	{
		// TODO Auto-generated method stub
		
		RestAssured.baseURI=("https://maps.googleapis.com");
		
		Response r= given().param("location", "-33.8670522,151.1957362").
		param("radius", "5"). 
		param("key", "AIzaSyCw5Lb3fU9Z7-VnJmyRzwg11ihV7_mBDYQ").log().all().
		when().get("/maps/api/place/nearbysearch/json").
		then().assertThat().statusCode(200).and().
		contentType(ContentType.JSON).
		and().body("results[0].place_id",equalTo("ChIJP3Sa8ziYEmsRUKgyFmh9AQM")).
		and().body("results[0].name",equalTo("Sydney")).
		and().header("server" , "scaffolding on HTTPServer2").extract().response();
		
		JsonPath js =ResusableMethods.rawToJson(r); 
		
		int count= js.get("results.size()");
		for(int i=0;i<count;i++) 
			
		{
			String namess=js.get("results["+i+" ].name");
			System.out.println(namess);
			
		}
		System.out.println(count); 

	}


	}
 

