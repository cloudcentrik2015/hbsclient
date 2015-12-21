package net.cloudcentrik.hbs;

import net.cloudcentrik.hbs.User;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
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

public class AdminView {

	JFrame frame;
	ArrayList<User> users;

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

		Object rowData[][] = createTableData(users);

		Object columnNames[] = { "User Name", "Password", "Email", "User Type","Select"};

		JTable table = new JTable(rowData, columnNames);
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
					JOptionPane.showMessageDialog(frame, "No Row Selected!!!");
				} else {
					int selectedRow = lsm.getMinSelectionIndex();

					int n = JOptionPane.showConfirmDialog(frame,
							"Would you delete this user?",
							"Delete User", JOptionPane.YES_NO_OPTION);
					if (n == JOptionPane.YES_OPTION) {
						
						JOptionPane.showMessageDialog(frame, "User Name : "
								+ users.get(selectedRow).getUserName());
					}
					
					lsm.clearSelection();

				}
			}
		});

		Border paneEdge = BorderFactory.createEmptyBorder(20, 20, 20, 20);

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setViewportView(table);
		scrollPane.setPreferredSize(new Dimension(750,200));
		

		JLabel hbslebel = new JLabel("HBS Admin Control Panel");

		
		
		JLabel lblUserName=new JLabel("User Name");
		JTextField txtUserName=new JTextField(20);
		JLabel lblUserPassward=new JLabel("Passward");
		JTextField txtUserPasward=new JTextField(20);
		JLabel lblUserType=new JLabel("Type");
		JTextField txtUserType=new JTextField(20);
		JButton btnUpdate=new JButton("Update");
		JButton btndelte=new JButton("Delete");
		
		JPanel jp=new JPanel();
		jp.setLayout(new GridLayout(4,2,10,10) );
		jp.setBorder(HBSClientUtils.createBorder("User",10));
		jp.add(lblUserName);
		jp.add(txtUserName);
		jp.add(lblUserPassward);
		jp.add(txtUserPasward);
		jp.add(lblUserType);
		jp.add(txtUserType);
		jp.add(btnUpdate);
		jp.add(btndelte);
		
		//frame.setLayout(new GridLayout(3,1,10,10));
		
		frame.setLayout(new FlowLayout());
		
		frame.add(hbslebel);
		frame.add(Box.createRigidArea(new Dimension(50,50)));

		frame.add(scrollPane);
		
		frame.add(jp);

		frame.setBackground(Color.LIGHT_GRAY);
		frame.setLocationRelativeTo(null);

		frame.setResizable(false);

		frame.pack();
		frame.setVisible(true);
	}

	private Object[][] createTableData(ArrayList<User> users) {
		Object[][] resultData = null;

		resultData = new Object[users.size()][];

		for (int i = 0; i < users.size(); i++) {

			User row = users.get(i);
			Object[] col = { row.getUserName(), row.getUserPassword(),
					row.getUserEmail(), row.getUserType(),new Boolean(false)};
			resultData[i] = col;

		}
		return resultData;
	}

}
