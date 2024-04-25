package APIs;

import java.util.regex.Pattern;

import java.util.regex.Matcher;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class LoginAPI extends APIBase {

	public LoginAPI(){

		super();

	}
	public void Login(String userName, String Password) {

		Response response;
		
		//retrieve user token and user cookies
		
		response = RestAssured.given()
				.header("Content-type", "application/json").header("accept", "application/json")
				.when()
				.get("/auth/login")
				.then()
				.extract().response();

		setUserToken(response);

		setUserCookies(response);

		//login with username and password
		response = RestAssured.given().contentType("application/x-www-form-urlencoded; charset=utf-8")
				.header("Cookie",orangehrmCookie).header("Content-type", "application/x-www-form-urlencoded").header("accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.")
				.header("Accept-Encoding","gzip, deflate, br, zstd")
				.formParam("username", userName).formParam("password", Password)
				.formParam("_token", token)
				.when()
				.post("/auth/validate")
				.then()
				.extract().response();
		
		resetUserCookies(response);



	}
	private void resetUserCookies(Response response) {

		String[] cookies;
		
		cookies= response.getDetailedCookies().toString().split(";");

		orangehrmCookie=cookies[0];
		
	}
	private void setUserCookies(Response response) {

		String[] cookies;

		cookies= response.getDetailedCookies().toString().split(";");

		orangehrmCookie=cookies[0];



	}
	private void setUserToken(Response response) {

		Pattern pattern = Pattern.compile("token=\"(.+?)\"");

		Matcher matcher = pattern.matcher(response.asString());

		if (matcher.find())
		{
			token=matcher.group(1);
			token  = token.replaceAll("&quot;", "");
		}

	}

}
