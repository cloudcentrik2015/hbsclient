package net.cloudcentrik.hbs;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class App {
	static JFrame frame;

	public static void main(String[] args) {
		// schedule this for the event dispatch thread (edt)
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				displayJFrame();
			}
		});
	}

	static void displayJFrame() {
		String[] httpMethods = { "GET", "POST", "PUT", "DELETE", "PUT" };
		frame = new JFrame("HBS Client Example");

		//create list
		JComboBox methodList = new JComboBox(httpMethods);
		methodList.setSelectedIndex(4);
		methodList.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// default title and icon
			}
		});
		//create Label
		JLabel urlLebel=new JLabel("URL"); 
		
		//create Textbox
		JTextField txtUrl=new JTextField(50);
		// create our jbutton
		JButton showDialogButton = new JButton("Restful API REQUEST");
		JButton postButton = new JButton("POST REQUEST");
		
		JTextArea resultBox=new JTextArea(6,60);
		resultBox.setText("REsult");

		// add the listener to the jbutton to handle the "pressed" event
		showDialogButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// default title and icon
				JOptionPane.showMessageDialog(frame, getRequest());

			}
		});

		// add the listener to the jbutton to handle the "pressed" event
		postButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				JOptionPane.showMessageDialog(frame, postRequest());

			}
		});

		// put the button on the frame
		
		frame.getContentPane().setLayout(new FlowLayout());
		frame.add(methodList);
		frame.add(urlLebel);
		frame.add(txtUrl);
		
		frame.add(showDialogButton);
		//frame.add(postButton);
		
		frame.add(resultBox);
		

		// set up the jframe, then display it
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setPreferredSize(new Dimension(700, 200));
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	@SuppressWarnings("finally")
	public static String getRequest() {

		String output = "ERROR";
		User s=new User();

		try {

			Client client = Client.create();

			WebResource webResource = client.resource("http://localhost:9000/User/1001");

			ClientResponse response = webResource.accept("application/json").get(ClientResponse.class);
			s=webResource.get(User.class);
			if (response.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			}

			output = response.getEntity(String.class);
			s=response.getEntity(User.class);
			System.out.println(s.getName());
			
			System.out.println("REquest Executed");

			return output;

		} catch (Exception e) {

			// e.printStackTrace();
			return output;

		} finally {
			return output;
		}

	}

	@SuppressWarnings("finally")
	public static String postRequest() {
		String output = "ERROR";
		try {

			Client client = Client.create();

			WebResource webResource = client.resource("http://localhost:9000/User/");

			String input = "{\"id\":\"1012\",\"name\":\"Test Customer\",\"email\":\"test@gmail.com\",\"phone\":\"07053498\"}";

			ClientResponse response = webResource.type("application/json").post(ClientResponse.class, input);

			if (response.getStatus() != 201) {
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			}

			System.out.println("Output from Server .... \n");
			output = response.getEntity(String.class);
			System.out.println(output);
			return output;

		} catch (Exception e) {

			//e.printStackTrace();
			return output;

		} finally {
			return output;
		}
	}

}
