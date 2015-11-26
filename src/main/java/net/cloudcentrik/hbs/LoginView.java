package net.cloudcentrik.hbs;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.*;

import net.cloudcentrik.hbs.AdminView;

public class LoginView {

	private JFrame frame;
	private JTextField userText;
	private JPasswordField passwordText;

	public LoginView() {
		createGUI();
	}
	
	public void createGUI() {
		HBSClientUtils.setLookAndFeel();
		frame = new JFrame("HBS Admin Login");
		frame.setSize(500,300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//frame.setLayout(new GridLayout(2,1,10,20));
		
		Container contentPane = frame.getContentPane();
		contentPane.setLayout(new FlowLayout());

		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(400,250));

		contentPane.add(new JLabel("Hair Dresser Booking System"));
		
		contentPane.add(panel);

		placeComponents(panel);

		frame.setLocationRelativeTo(null);

		frame.setResizable(false);

		frame.setVisible(true);
	}

	private void placeComponents(JPanel panel) {

		panel.setBorder(HBSClientUtils.createBorder("login",30));
		panel.setLayout(new GridLayout(3,2,10,20));
		//panel.setBackground(Color.LIGHT_GRAY);

		JLabel userLabel = new JLabel("User");
		userLabel.setHorizontalAlignment(JLabel.RIGHT);
		panel.add(userLabel);

		userText = new JTextField(20);
		panel.add(userText);

		JLabel passwordLabel = new JLabel("Password");
		passwordLabel.setHorizontalAlignment(JLabel.RIGHT);
		panel.add(passwordLabel);

		passwordText = new JPasswordField(20);
		panel.add(passwordText);
		
		JButton registerButton = new JButton("register");
		panel.add(registerButton);

		registerButton.setActionCommand("register");
		registerButton.addActionListener(new loginButtonListener());

		JButton loginButton = new JButton("Login");
		panel.add(loginButton);

		loginButton.setActionCommand("login");
		loginButton.addActionListener(new loginButtonListener());

		
	}

	public class loginButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			

			if ("login".equals(e.getActionCommand())) {

				
				
				User u = new User();
				u.setUserName(userText.getText());
				u.setUserPassword(new String(passwordText.getPassword()));
				u.setUserEmail("");
				u.setUserType("");

				String url = "http://localhost:9000/User/signin";
				
				if (LoginRequest.isValidUser(url, u)) {
					new AdminView();
				}
				/*char[] p = passwordText.getPassword();
				if (LoginRequest.login(userText.getText(), new String(p))) {
					new AdminView();
				}*/ 
				else {
					JOptionPane.showMessageDialog(frame, "Login incorrect!!!");
					userText.setText("");
					passwordText.setText("");
				}

			} else if ("register".equals(e.getActionCommand())) {

				/*
				 * JOptionPane.showMessageDialog(frame,
				 * "Action yet to implemented: register");
				 */

				new RegisterView();

			}

		}

	}

}
