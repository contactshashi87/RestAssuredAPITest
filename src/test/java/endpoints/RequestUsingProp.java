package endpoints;

import static io.restassured.RestAssured.given;

import java.util.ResourceBundle;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import payload.User;

public class RequestUsingProp {
	
			//Method to get endpoint url using properties file

			static ResourceBundle getURL(){
			ResourceBundle rb =ResourceBundle.getBundle("routes"); 
			return rb;
			
			}
			//Post the user
			public static Response createUser(User payload) {
				
				String postUrl=getURL().getString("post_url");
		
				Response response = given()
									.contentType(ContentType.JSON)
									.accept(ContentType.JSON)
									.body(payload)
									.when()
									.post(postUrl);
				return response;
			}
		
			//get the user
			public static Response getUser(String userName) {
				
				String getUrl=getURL().getString("get_url");

				Response response = given()
									.pathParam("username", userName)
									.when()
									.get(getUrl);
				return response;
			}
			
			//update the user
			public static Response updateUser(String userName, User payload) {
				
				String updateUrl=getURL().getString("update_url");

				Response response = given()
									.contentType(ContentType.JSON)
									.accept(ContentType.JSON)
									.pathParam("username", userName)
									.body(payload)
									.when()
									.put(updateUrl);
				return response;
			}
			//delete the user
			public static Response deleteUser(String userName) {
				
				String deleteUrl=getURL().getString("delete_url");

				Response response = given()
									.pathParam("username", userName)
									.when()
									.delete(deleteUrl);
				return response;
			}	

}
