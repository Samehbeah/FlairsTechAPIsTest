package APIs;

import java.util.HashMap;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class AddNewCandidateAPI extends APIBase {

	public AddNewCandidateAPI() {

		super();

	}



	public HashMap<String, Integer> createCandidate(String firstName, String middleName, String lastName, String email, String contactNumber , String keywords, String comment, String dateOfApplication, boolean consentToKeepData, int vacancyId ) {


		String requestBody = "{\"firstName\":\"" +firstName+"\",\"middleName\":\"" +middleName+"\",\"lastName\":\"" +lastName+"\",\"email\":\"" +email+"\",\"contactNumber\":"+contactNumber+",\"keywords\":"+keywords+",\"comment\":"+comment+",\"dateOfApplication\":\"" +dateOfApplication+"\",\"consentToKeepData\":"+consentToKeepData+",\"vacancyId\":"+vacancyId+"}";
		Response response = RestAssured.given()
				.header("Content-type", "application/json").header("accept", "application/json")
				.header("Cookie",orangehrmCookie)
				.and()
				.body(requestBody)
				.when()
				.post("/api/v2/recruitment/candidates")
				.then()
				.extract().response();

		System.out.println(response.statusCode());

		JsonPath jsonPath = new JsonPath(response.asString());

		int candidate_id= jsonPath.getInt("data.id");

		HashMap<String, Integer> response_data = new HashMap<String, Integer>();

		response_data.put("response_code", Integer.valueOf(response.statusCode()));

		response_data.put("candidate_id", candidate_id);


		return response_data ;
	}


}
