package LibararyResources;

import static org.hamcrest.Matchers.equalTo;

import io.restassured.http.ContentType;

public class AddLibararyBody {

	public static String bodys(String name, String isbn, String aisle, String author) {

		String PostRequest = ("{" +

				"\"name\":\""+name+" \"," + "\"isbn\":\""+isbn+"\"," + "\"aisle\":\""+aisle+" \","
				+ "\"author\":\""+author+"\"" + "}");
		return PostRequest;

	}
	
	
	public static String bodyDelete(String id)
	{
		
		String DeleteRequest=  (
"{"+
 
"\"id\":\""+id+"\"" + "}");

				
		return DeleteRequest;
		
	}

}
