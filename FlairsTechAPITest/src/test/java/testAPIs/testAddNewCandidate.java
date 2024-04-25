package testAPIs;

import java.util.HashMap;
import org.testng.Assert;
import org.testng.annotations.Test;
import APIs.AddNewCandidateAPI;
import APIs.DeleteUserAPI;
import APIs.LoginAPI;


public class testAddNewCandidate {
	
	int user_id;
	int candidate_id;
	@Test
	public void createCandidate() {
		
	// first login to OrangHrm	
    new LoginAPI().Login("Admin", "admin123");
    
	
	HashMap<String, Integer> response_data= new AddNewCandidateAPI().createCandidate("Sameh", "Hussien", "Mohamed", "test@test.com", null, null, null, "2024-04-25", false, 6);
	candidate_id = (int)response_data.get("candidate_id");
	int response_code = (int)response_data.get("response_code");
	Assert.assertEquals(response_code, 200);
	
	
	
	}
	@Test
	public void deleteCandidate() {
		
		int response_code = new DeleteUserAPI().deleteCandidate(candidate_id);
		
		Assert.assertEquals(response_code, 200);
		
	}

}
