package net.cloudcentrik.hbs;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;

public class DeleteUserRequest {
	
	public static boolean deleteUser(String url,User user) {
		boolean output = false;
		try {

			ClientConfig clientConfig = new DefaultClientConfig();
			clientConfig.getFeatures().put(
					JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
			Client client = Client.create(clientConfig);
			

			WebResource webResource = client.resource(url);
			
			System.out.println(user.getUserEmail());
			
			webResource.queryParam("email",user.getUserEmail());
			
			ClientResponse response = webResource.type("application/json")
					.delete(ClientResponse.class,String.class);

			if (response.getStatus() != 200) {
				
				throw new RuntimeException("Failed : HTTP error code : "
						+ response.getStatus());
			}
			
			output=true;
			
			return output;

		} catch (Exception e) {
			e.printStackTrace();
			return output;
		} 
	}

}
