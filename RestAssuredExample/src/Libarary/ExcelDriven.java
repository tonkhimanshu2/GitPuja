package Libarary;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.net.URI;
import java.util.Properties;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import LibararyResources.*;
import PropertiesPackage.ResusableMethods;

public class ExcelDriven {

	private static final String Id = null;
	static Properties prop = new Properties();

	@BeforeTest
	public void getDataProperties() throws IOException {

		FileInputStream fs = new FileInputStream(
				"C:\\users\\minky\\eclipse-workspace\\RestAssuredExample\\src\\LibararyResources\\Liba.Properties");
		prop.load(fs);

	}

	@Test (dataProvider = "BookData")
	
	
	
	public static void libararyMethods(String name,String isbn,String aisle, String author) {

		RestAssured.baseURI = prop.getProperty("HOST");

		Response res = given().header("content-type", "application/json")
				.body(AddLibararyBody.bodys(name,isbn,aisle,author))
				.when()
				.post(Resources.PlacePostResourcesAdd())
				.then().assertThat().statusCode(200)
				.extract().response();

		JsonPath js = ResusableMethods.rawToJson(res);
		String id = js.getString("ID");
		System.out.println(id); 
		
		System.out.println("add completed");

	// Delete Books added one
		given().header("content-type", "application/json").
		body(AddLibararyBody.bodyDelete(id)).
		when().post(Resources.DeleteResourcses()).
		then().assertThat().statusCode(200).and().contentType(ContentType.JSON).and().body("status" , equalTo("Ok")).and()
		.and().extract().response(); 
		System.out.println("delete completed");
		
	}
	
	
	
	
	@DataProvider(name="BookData")
	public Object[][] getdata()
	{
	return new	Object[][] {{"qrlko","24934","8134","qwkrr"},
			{"qalio","eu4","11111","qrerk"},
			{"qqkaaqelo","erk434","121910","kqwer"}};
			
		}
	}

