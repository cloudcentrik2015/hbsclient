package net.cloudcentrik.hbs;

import net.cloudcentrik.hbs.User;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

public class AdminView {

	JFrame frame;
	ArrayList<User> users;
	private Object rowData[][];
	private DefaultTableModel defTableModel;

	private JTextField txtUserName;
	private JTextField txtUserPasward;
	private JTextField txtUserEmail;
	private JTextField txtUserType;
	private JButton btnUpdate;
	private JButton btndelte;
	private JComboBox cmbUserType;
	private JTable table;

	public AdminView() {
		createAndShowGUI();
	}

	public void createAndShowGUI() {
		JFrame.setDefaultLookAndFeelDecorated(true);
		HBSClientUtils.setLookAndFeel();
		frame = new JFrame("HBS Admin");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		frame.setSize(800, 600);

		// get all user JSON request
		users = GetAllUser.getAllUser("http://localhost:9000/User/all");

		rowData= createTableData(users);

		Object columnNames[] = { "User Name", "Password", "Email", "User Type",
				"Select" };
		
		defTableModel = new DefaultTableModel(rowData,columnNames);

		table = new JTable(defTableModel);
		table.setRowHeight(30);
		table.setBackground(Color.CYAN);
		table.setForeground(Color.blue);
		table.setPreferredScrollableViewportSize(table.getPreferredSize());

		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		ListSelectionModel rowSM = table.getSelectionModel();
		rowSM.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				// Ignore extra messages.
				if (e.getValueIsAdjusting())
					return;

				ListSelectionModel lsm = (ListSelectionModel) e.getSource();
				if (lsm.isSelectionEmpty()) {
					// JOptionPane.showMessageDialog(frame,
					// "No Row Selected!!!");
				} else {
					int selectedRow = lsm.getMinSelectionIndex();

					/*
					 * int n = JOptionPane.showConfirmDialog(frame,
					 * "Would you delete this user?", "Delete User",
					 * JOptionPane.YES_NO_OPTION); if (n ==
					 * JOptionPane.YES_OPTION) {
					 * 
					 * JOptionPane.showMessageDialog(frame, "User Name : " +
					 * users.get(selectedRow).getUserName()); }
					 */

					txtUserName.setText(users.get(selectedRow).getUserName());
					txtUserPasward.setText(users.get(selectedRow)
							.getUserPassword());
					txtUserType.setText(users.get(selectedRow).getUserType());
					txtUserEmail.setText(users.get(selectedRow).getUserEmail());
					
					if(users.get(selectedRow).getUserType().equals("admin")){
						cmbUserType.setSelectedIndex(0);	
					}else if(users.get(selectedRow).getUserType().equals("customer")){
						cmbUserType.setSelectedIndex(1);
					}else if(users.get(selectedRow).getUserType().equals("shop")) {
						cmbUserType.setSelectedIndex(2);
					}else{
						cmbUserType.setSelectedIndex(1);
					}
					
					

					//lsm.clearSelection();

				}
			}
		});

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setViewportView(table);
		scrollPane.setPreferredSize(new Dimension(750, 200));

		JLabel hbslebel = new JLabel("HBS Admin Control Panel");

		JLabel lblUserName = new JLabel("User Name");
		txtUserName = new JTextField(20);
		JLabel lblUserPassward = new JLabel("Passward");
		txtUserPasward = new JTextField(20);
		JLabel lblUserEmail = new JLabel("Email");
		txtUserEmail = new JTextField(20);
		txtUserEmail.setEditable(false);
		txtUserEmail.setBackground(Color.darkGray);
		JLabel lblUserType = new JLabel("Type");
		txtUserType = new JTextField(20);
		
		String[] userType = { "admin", "customer", "shop" };
		cmbUserType = new JComboBox(userType);
		cmbUserType.setSelectedIndex(1);
		cmbUserType.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				JComboBox cb = (JComboBox) e.getSource();
				String uType = (String) cb.getSelectedItem();
				txtUserType.setText(uType);

			}
		});
		
		btnUpdate = new JButton("Update");
		btndelte = new JButton("Delete");

		// Add action listener to update button
		btnUpdate.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// Execute when button is pressed
				// frame.dispose();
				JOptionPane.showMessageDialog(frame,
						"Action not yet implemented!!!");
			}
		});

		// Add action listener to delete button
		btndelte.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// Execute when button is pressed
				// frame.dispose();
				String userType=cmbUserType.getSelectedItem().toString();
				User user=new User(txtUserName.getText(),txtUserPasward .getText(),txtUserEmail.getText(),userType);
				if(DeleteUserRequest.deleteUser("http://localhost:9000/User/"+txtUserEmail.getText(), user)){
					JOptionPane.showMessageDialog(frame,
							"User Deleted!!!");
					updateTable();
				}else{
					JOptionPane.showMessageDialog(frame,
							"Error Deleting User!!!");
				}
				
			}
		});

		JPanel jp = new JPanel();
		jp.setLayout(new GridLayout(5, 2, 10, 10));
		jp.setBorder(HBSClientUtils.createBorder("User", 10));
		jp.add(lblUserName);
		jp.add(txtUserName);
		jp.add(lblUserPassward);
		jp.add(txtUserPasward);
		jp.add(lblUserType);
		jp.add(cmbUserType);
		jp.add(lblUserEmail);
		jp.add(txtUserEmail);
		jp.add(btnUpdate);
		jp.add(btndelte);
		

		// frame.setLayout(new GridLayout(3,1,10,10));

		frame.setLayout(new FlowLayout());

		frame.add(hbslebel);
		frame.add(Box.createRigidArea(new Dimension(50, 50)));

		frame.add(scrollPane);

		frame.add(jp);

		frame.setBackground(Color.LIGHT_GRAY);
		frame.setLocationRelativeTo(null);

		frame.setResizable(false);

		// frame.pack();
		frame.setVisible(true);
	}

	private Object[][] createTableData(ArrayList<User> users) {
		Object[][] resultData = null;

		resultData = new Object[users.size()][];

		for (int i = 0; i < users.size(); i++) {

			User row = users.get(i);
			Object[] col = { row.getUserName(), row.getUserPassword(),
					row.getUserEmail(), row.getUserType(), new Boolean(false) };
			resultData[i] = col;

		}
		return resultData;
	}
	
	private void updateTable(){
		defTableModel.setRowCount(0);
		users = GetAllUser.getAllUser("http://localhost:9000/User/all");
	    rowData= createTableData(users);
	    
	    for (int i = 0; i < rowData.length; i++) {

	    	defTableModel.addRow(rowData[i]);
	    }
	    
	    defTableModel.fireTableDataChanged();
	}

}
