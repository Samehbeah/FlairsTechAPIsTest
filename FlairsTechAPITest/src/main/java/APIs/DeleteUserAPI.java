package APIs;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class DeleteUserAPI extends APIBase {

	public DeleteUserAPI() {

		super();

	}

	public int  deleteUser(int user_id) {
		String request_body = "{\"ids\":["+user_id+"]}";

		Response response = RestAssured.given().header("Content-Type","application/json")
				.header("Cookie",orangehrmCookie).and().body(request_body)
				.when().delete("/api/v2/admin/users").then().extract().response();
		System.out.println(response.getStatusCode());
		return response.getStatusCode();
	}

	public int deleteCandidate(int candidate_id) {
	
		String request_body = "{\"ids\":["+candidate_id+"]}";

		Response response = RestAssured.given().header("Content-Type","application/json")
				.header("Cookie",orangehrmCookie).and().body(request_body)
				.when().delete("/api/v2/recruitment/candidates").then().extract().response();
		System.out.println(response.getStatusCode());
		return response.getStatusCode();
		
	}
}
