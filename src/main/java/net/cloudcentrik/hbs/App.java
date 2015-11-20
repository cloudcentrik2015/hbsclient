package net.cloudcentrik.hbs;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import net.cloudcentrik.hbs.LoginView;

public class App {
	static JFrame frame;
	static JTextField txtUrl;
	static JLabel urlLebel;
	static JTextArea resultBox;

	static JButton getRequestButton;
	static JButton postRequestButton;

	public static void main(String[] args) {

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				//displayJFrame();
				new LoginView();
			}
		});
	}
	
	/*

	static void displayJFrame() {

		frame = new JFrame("HBS Client Example");

		// create Label
		urlLebel = new JLabel("URL");

		// create Textbox
		txtUrl = new JTextField(50);

		// create buttons
		getRequestButton = new JButton("GET REQUEST");
		postRequestButton = new JButton("POST REQUEST");

		resultBox = new JTextArea(6, 60);
		resultBox.setText("REsult");

		// add the listener to the jbutton to handle the "pressed" event
		getRequestButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// default title and icon
				resultBox.setText("");
				resultBox.append(ServiceRequest.getRequest(txtUrl.getText()));
			}
		});

		// add the listener to the jbutton to handle the "pressed" event
		postRequestButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// default title and icon
				//resultBox.setText("posting...");
				//resultBox.append(ServiceRequest.postRequest(txtUrl.getText()));
				new PostFrame();
			}
		});

		// put the button on the frame

		frame.getContentPane().setLayout(new FlowLayout());
		frame.add(urlLebel);
		frame.add(txtUrl);

		frame.add(getRequestButton);
		frame.add(postRequestButton);

		frame.add(resultBox);

		// set up the jframe, then display it
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setPreferredSize(new Dimension(700, 200));
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	*/

}
