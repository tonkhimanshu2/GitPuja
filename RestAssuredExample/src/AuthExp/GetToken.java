package AuthExp; 
import static io.restassured.RestAssured.given; 

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
import pojo.GetCoursesPojo;



public class GetToken {   
	


	public static void main(String[] args) throws InterruptedException  
	{
	
	/*{ 
				
		getTokens();
		acessTokens(); 
		getAuth();
	}
	*/
	
	//get authorization
	
//	public static String getAuth()  throws InterruptedException
	//{
		System.setProperty("webdriver.chrome.driver", "D://chromedriver.exe");
		WebDriver wd=new ChromeDriver(); 
		wd.get("https://accounts.google.com/o/oauth2/v2/auth?scope=https://www.googleapis.com/auth/userinfo.email&auth_url=https://accounts.google.com/o/oauth2/v2/auth&client_id=692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com&response_type=code&redirect_uri=https://rahulshettyacademy.com/getCourse.php&state=verifyfjdss");
		wd.findElement(By.cssSelector("input[type='email']" )).sendKeys("testphantom12@gmail.com");
		wd.findElement(By.cssSelector("input[type='email']" )).sendKeys(Keys.ENTER);
		Thread.sleep(3000);
		wd.findElement(By.cssSelector("input[type='password']" )).sendKeys("welcome@123");
		wd.findElement(By.cssSelector("input[type='password']" )).sendKeys(Keys.ENTER);
		Thread.sleep(4000); 
		
		String str= wd.getCurrentUrl();
		String partialString= str.split("code=")[1];
		String code= partialString.split("&scope")[0];
		System.out.println(code);				
	//	return code; 
		
		
	//}
	
	
	//public static String getTokens() throws InterruptedException
	//{
	
	//
		String access_token= given().urlEncodingEnabled(false)
		.queryParams("code", code)
		.queryParam("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
		.queryParam("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
		.queryParam("redirect_uri", "https://rahulshettyacademy.com/getCourse.php")
		.queryParam("grant_type","authorization_code").
		when().log().all().
		post("https://www.googleapis.com/oauth2/v4/token").asString(); 
		JsonPath js=new JsonPath(access_token);
		String accessToken= js.getString("access_token");
		//return accessToken;
	
	
	//public static String acessTokens() throws InterruptedException {
	
	// for get Courses
	
		GetCoursesPojo gcp= given().queryParam("access_token", accessToken ).expect().defaultParser(Parser.JSON).
		when().
		get("https://rahulshettyacademy.com/getCourse.php").as(GetCoursesPojo.class);
		System.out.println(gcp.getLikedin());
	//	System.out.println(gcp.getClass());
	     
		//System.out.println(gcp.getExpertise());
		System.out.println(gcp.getInstructor());
		
	
	
	}
	

}
	


