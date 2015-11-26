package net.cloudcentrik.hbs;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;

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
		frame.setSize(300, 180);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel();

		frame.add(panel);

		placeComponents(panel);

		frame.setLocationRelativeTo(null);

		frame.setResizable(false);

		frame.setVisible(true);
	}

	private void placeComponents(JPanel panel) {

		panel.setLayout(null);

		JLabel hbsLabel = new JLabel("HBS Dextop CLIENT");
		hbsLabel.setBounds(100, 120, 150, 25);
		panel.add(hbsLabel);

		JLabel userLabel = new JLabel("User");
		userLabel.setBounds(10, 10, 80, 25);
		panel.add(userLabel);

		userText = new JTextField(20);
		userText.setBounds(100, 10, 160, 25);
		panel.add(userText);

		JLabel passwordLabel = new JLabel("Password");
		passwordLabel.setBounds(10, 40, 80, 25);
		panel.add(passwordLabel);

		passwordText = new JPasswordField(20);
		passwordText.setBounds(100, 40, 160, 25);
		panel.add(passwordText);

		JButton loginButton = new JButton("login");
		loginButton.setBounds(50, 80, 100, 25);
		panel.add(loginButton);

		loginButton.setActionCommand("login");
		loginButton.addActionListener(new loginButtonListener());

		JButton registerButton = new JButton("register");
		registerButton.setBounds(160, 80, 100, 25);
		panel.add(registerButton);

		registerButton.setActionCommand("register");
		registerButton.addActionListener(new loginButtonListener());
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
