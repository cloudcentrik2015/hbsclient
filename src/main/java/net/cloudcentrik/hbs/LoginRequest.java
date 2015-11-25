package net.cloudcentrik.hbs;

import java.util.Arrays;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;

public class LoginRequest {

	public static boolean login(String userName, String password) {

		if (userName.equals("admin") && password.equals("admin")) {
			return true;
		}else{
			return false;
		}
	}

	private static boolean isPasswordCorrect(char[] input) {
		boolean isCorrect = true;
		char[] correctPassword = { 'a', 'd', 'm', 'i', 'n'};

		if (input.length != correctPassword.length) {
			isCorrect = false;
		} else {
			isCorrect = Arrays.equals(input, correctPassword);
		}

		// Zero out the password.
		Arrays.fill(correctPassword, '0');

		return isCorrect;
	}

	public static boolean isValidUser(String url,User user) {
		boolean output = false;
		try {

			ClientConfig clientConfig = new DefaultClientConfig();
			clientConfig.getFeatures().put(
					JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
			Client client = Client.create(clientConfig);

			WebResource webResource = client.resource(url);
			
			ClientResponse response = webResource.type("application/json")
					.post(ClientResponse.class, user);

			if (response.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ response.getStatus());
			}

			User u = response.getEntity(User.class);
			
			//System.out.println(u.getUserName());
			
			if(u.getUserName().equals("")&&u.getUserPassword().equals("")){
				output=false;
			}else{
				output=true;
			}
			return output;

		} catch (Exception e) {

			e.printStackTrace();
			return output;

		} finally {

		}
	}

}
