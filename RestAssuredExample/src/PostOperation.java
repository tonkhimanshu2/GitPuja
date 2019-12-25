import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import io.restassured.RestAssured;
import io.restassured.http.ContentType; 
import static org.hamcrest.Matchers.equalTo;


public class PostOperation {
	
	@Test
	
	public void PostData()
	{
		//Task1 - Grab the response
		
		RestAssured.baseURI=("http://216.10.245.166");
		given().
		queryParam("key", "qaclick123"). 
		body("{"+
"\"location\":{"+
	"\"lat\" : -38.383494,"+
	"\"lng\" : 33.427362"+
"},"+

"\"accuracy\" : 5,"+
"\"name\" : \"FrontlineHouse\","+
"\"phone_number\": \"(+91 123456789)\","+
"\"address\" : \"123 AB street\","+ 
"\"language\" :  \"English\""+

"}").
		when().
		post("/maps/api/place/add/json").
		then().assertThat().statusCode(200).and().contentType(ContentType.JSON).and().body("status", equalTo("OK"));
		
		
	}

}
