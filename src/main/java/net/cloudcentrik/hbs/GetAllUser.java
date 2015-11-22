package net.cloudcentrik.hbs;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.MediaType;

import org.codehaus.jackson.jaxrs.JacksonJaxbJsonProvider;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;

public class GetAllUser {
	
	public static ArrayList<User> getAllUser(String urlTxt) {

		ArrayList<User> allUser=null;

		try {

			ClientConfig config = new DefaultClientConfig();
			config.getClasses().add(JacksonJaxbJsonProvider.class);
			config.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING,
					Boolean.TRUE);
			Client client = Client.create(config);

			WebResource webResource = client.resource(urlTxt);
			
			ClientResponse response = webResource.accept(
					MediaType.APPLICATION_JSON).get(ClientResponse.class);

			if (response.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ response.getStatus());
			}

			List<User> userList = response
					.getEntity(new GenericType<List<User>>() {
					});

			allUser=new ArrayList<User>(userList);
			
			System.out.println("REquest Executed");

			return allUser;

		} catch (Exception e) {

			return allUser;

		} finally {
			//
		}
	}

}
