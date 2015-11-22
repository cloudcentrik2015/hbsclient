package net.cloudcentrik.hbs;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;

public class LoginRequest {
	
	public static boolean login(String userName,String password){
		
		return false;
	}
	
	public static String postRequest(String url) {
		String output = "ERROR";
		try {

			ClientConfig clientConfig = new DefaultClientConfig();
			clientConfig.getFeatures().put(
					JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
			Client client = Client.create(clientConfig);

			WebResource webResource = client.resource(url);
			// String input =
			// "{\"id\":\"1012\",\"name\":\"Test Customer\",\"email\":\"test@gmail.com\",\"phone\":\"07053498\"}";

			User user = new User("jamal", "ith987", "onetest@test", "shop");
			ClientResponse response = webResource.type("application/json")
					.post(ClientResponse.class, user);

			if (response.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ response.getStatus());
			}
			
			System.out.println("Output from Server .... \n");
			output = response.getEntity(String.class);
			//System.out.println(output);
			return output;

		} catch (Exception e) {

			e.printStackTrace();
			return output;

		} finally {

		}
	}

}
