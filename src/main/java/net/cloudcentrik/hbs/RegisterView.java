package net.cloudcentrik.hbs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.Border;

public class RegisterView {

	private JFrame frame;
	private JTextField txtUserName;
	private JTextField txtPassword;
	private JTextField txtEmail;
	private JTextField txtUserType;

	public RegisterView() {

		displayPostForm();
	}
	
	private void displayPostForm() {
		HBSClientUtils.setLookAndFeel();

		frame = new JFrame("Signup");
		frame.setLayout(new GridLayout());
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		frame.setPreferredSize(new Dimension(600, 400));
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);

		this.createPostForm(frame.getContentPane());
		// Display the window.
		frame.setLocationRelativeTo(null);
		frame.pack();
		frame.setVisible(true);

	}

	public void createPostForm(Container contentPane) {

		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());

		GridBagConstraints constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets = new Insets(10, 10, 10, 10);

		JLabel userName = new JLabel("User Name");
		JLabel password = new JLabel("Password");
		JLabel email = new JLabel("Email");

		String[] userType = { "admin", "customer", "shop" };
		JComboBox cmbUser = new JComboBox(userType);
		cmbUser.setSelectedIndex(1);
		cmbUser.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				JComboBox cb = (JComboBox) e.getSource();
				String uType = (String) cb.getSelectedItem();
				txtUserType.setText(uType);

			}
		});

		JLabel type = new JLabel("User Type");

		txtUserName = new JTextField(20);
		txtPassword = new JTextField(20);
		txtEmail = new JTextField(20);
		txtUserType = new JTextField(20);

		JButton btnSave = new JButton("Save");
		JButton btnClose = new JButton("Close");

		// add components to the panel
		constraints.gridx = 0;
		constraints.gridy = 0;
		panel.add(userName, constraints);
		constraints.gridx = 1;
		panel.add(txtUserName, constraints);

		constraints.gridx = 0;
		constraints.gridy = 1;
		panel.add(password, constraints);
		constraints.gridx = 1;
		panel.add(txtPassword, constraints);

		constraints.gridx = 0;
		constraints.gridy = 2;
		panel.add(email, constraints);
		constraints.gridx = 1;
		panel.add(txtEmail, constraints);

		constraints.gridx = 0;
		constraints.gridy = 3;
		panel.add(type, constraints);
		constraints.gridx = 1;
		panel.add(cmbUser, constraints);
		// constraints.gridx = 2;
		// panel.add(txtUserType, constraints);

		constraints.gridx = 0;
		constraints.gridy = 4;
		constraints.anchor = GridBagConstraints.CENTER;
		panel.add(btnSave, constraints);
		constraints.gridx = 1;
		constraints.anchor = GridBagConstraints.CENTER;
		panel.add(btnClose, constraints);

		btnSave.setActionCommand("save");
		btnClose.setActionCommand("close");

		// Add action listener to button
		btnSave.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// Execute when button is pressed
				postData(txtUserName.getText(), txtPassword.getText(),
						txtEmail.getText(), txtUserType.getText());
				txtUserName.setText("");
				txtPassword.setText("");
				txtEmail.setText("");
				txtUserType.setText("");
			}
		});

		// Add action listener to button
		btnClose.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// Execute when button is pressed
				frame.dispose();
			}
		});

		Border emptyBorder = BorderFactory.createEmptyBorder(20, 20, 20, 20);
		Border txtBorder = BorderFactory.createTitledBorder(
				BorderFactory.createEtchedBorder(), "New User");
		Border compoundBoarder = BorderFactory.createCompoundBorder(
				emptyBorder, txtBorder);

		panel.setBorder(compoundBoarder);
		// panel.setBackground(Color.WHITE);

		contentPane.add(panel, BorderLayout.CENTER);

	}

	public void postData(String userName, String password, String email,
			String type) {
		// post data
		JOptionPane.showMessageDialog(frame, "Action not yet implemented");
	}
}
