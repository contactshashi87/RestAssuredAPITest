package test;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import endpoints.Requests;
import io.restassured.response.Response;
import payload.User;

public class UserTests {

	Faker faker;
	User userPayload;

	@BeforeClass
	public void setupData() {

		faker = new Faker();
		userPayload = new User();

		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setUsername(faker.name().username());
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		userPayload.setPassword(faker.internet().password(5, 10));
		userPayload.setPhone(faker.phoneNumber().cellPhone());
		userPayload.setUserStatus(faker.idNumber().hashCode());

		System.out.println(userPayload);
		System.out.println(faker.name().firstName());

	}

	@Test(priority = 1)
	public void postUser() {
		System.out.println("post request entered");
		Response response = Requests.createUser(userPayload);
		Assert.assertEquals(response.getStatusCode(), 200);
		System.out.println("User created with username: " + userPayload.getUsername());

	}

	@Test(priority = 2)
	public void testGetUserByName() {
		System.out.println("Looking for username: " + userPayload.getUsername());
		Response response = Requests.getUser(userPayload.getUsername());
		response.then().log().all();

		Assert.assertEquals(response.getStatusCode(), 200);
		System.out.println("User retrieved with username: " + userPayload.getUsername());
	}

	@Test(priority = 3)
	public void testUpdateUserByName() {
		// updating the required details
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());

		Response response = Requests.updateUser(this.userPayload.getUsername(), userPayload);
		// chai assertion available in Rest Assured
		response.then().log().body().statusCode(200);
		// TestNG assertion
		Assert.assertEquals(response.getStatusCode(), 200);

		// Checking data updated or not
		Response responseAfterUpdate = Requests.getUser(this.userPayload.getUsername());
		Assert.assertEquals(responseAfterUpdate.getStatusCode(), 200);
		System.out.println(responseAfterUpdate.getBody().asString());
	}

	@Test(priority = 4)
	public void testDeleteUserByName() {
		Response response = Requests.deleteUser(this.userPayload.getUsername());
		Assert.assertEquals(response.getStatusCode(), 200);
	}

}
