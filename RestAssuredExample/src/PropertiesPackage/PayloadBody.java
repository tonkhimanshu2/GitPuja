package PropertiesPackage;

public class PayloadBody { 
	
	
	public static String getPostData()
	{
		
		String bodyData="{"+
				"\"location\":{"+
				"\"lat\" : -38.383494,"+
				"\"lng\" : 33.427362"+
			"},"+

			"\"accuracy\" : 5,"+
			"\"name\" : \"FrontlineHouse\","+
			"\"phone_number\": \"(+91 123456789)\","+
			"\"address\" : \"123 AB street\","+
			"\"language\" :  \"English\""+

			"}";
		return bodyData;
		
	}

	

}
