package test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import endpoints.Requests;
import io.restassured.response.Response;
import payload.User;
import utilities.DataProviders;

		

public class DataDrivenTest {
	
	public Logger logger;

	@Test(priority = 1, dataProvider = "Data", dataProviderClass = DataProviders.class)
	public void testPostuser(String userID, String userName, String firstName, String lastName, String userEmail,
			String pwd, String phn) {

		User userPayload = new User();
		userPayload.setId(Integer.parseInt(userID));
		userPayload.setUsername(userName);
		userPayload.setFirstName(firstName);
		userPayload.setLastName(lastName);
		userPayload.setEmail(userEmail);
		userPayload.setPassword(pwd);
		userPayload.setPhone(phn);
		
		logger=LogManager.getLogger(this.getClass());
		
		logger.info("Posting user");
		Response response = Requests.createUser(userPayload);
		Assert.assertEquals(response.getStatusCode(), 200);
		System.out.println("User created with username: " + userPayload.getUsername());
		Reporter.log("User created and logged successfully");
		logger.info("Posted the user");
	}
	
	@Test(priority = 2, dataProvider = "UserNames", dataProviderClass = DataProviders.class)
	public void testDeleteUsewr(String userName) {
		
		logger.info("Deleting user");
		Response response = Requests.deleteUser(userName);
		System.out.println("Deleting "+"'"+userName+"'"+ " from database");
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("Deleted the user");
		
	}

}
