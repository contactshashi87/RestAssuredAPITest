package endpoints;

import io.restassured.*;
import io.restassured.http.ContentType;
import io.restassured.response.*;
import payload.User;

import static io.restassured.RestAssured.*;

public class Requests {
	
		//Post the user
		public static Response createUser(User payload) {
	
			Response response = given()
								.contentType(ContentType.JSON)
								.accept(ContentType.JSON)
								.body(payload)
								.when()
								.post(Routes.post_url);
			return response;
		}
	
		//get the user
		public static Response getUser(String userName) {

			Response response = given()
								.pathParam("username", userName)
								.when()
								.get(Routes.get_url);
			return response;
		}
		
		//update the user
		public static Response updateUser(String userName, User payload) {

			Response response = given()
								.contentType(ContentType.JSON)
								.accept(ContentType.JSON)
								.pathParam("username", userName)
								.body(payload)
								.when()
								.put(Routes.update_url);
			return response;
		}
		//delete the user
		public static Response deleteUser(String userName) {

			Response response = given()
								.pathParam("username", userName)
								.when()
								.delete(Routes.delete_url);
			return response;
		}	

}
