package APIs;

import io.restassured.RestAssured;

public class APIBase {
	static String orangehrmCookie;
	static String token;
	APIBase(){
	
	RestAssured.baseURI = "https://opensource-demo.orangehrmlive.com/web/index.php";

	
	}
}
