package net.cloudcentrik.hbs;

import net.cloudcentrik.hbs.User;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
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
		HBSClientUtils.setLookAndFeel();
		frame = new JFrame("HBS Admin");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		frame.setSize(800, 400);
		
		//get all user JSON request
		users=GetAllUser.getAllUser("http://localhost:9000/User/all");

		Object rowData[][]=createTableData(users);
		
		Object columnNames[] = { "User Name", "Password", "Email", "User Type" };
		
		JTable table = new JTable(rowData, columnNames);
		table.setRowHeight(30);
		table.setBackground(Color.CYAN);
		table.setForeground(Color.blue);
		
		 table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		 
		 ListSelectionModel rowSM = table.getSelectionModel();
         rowSM.addListSelectionListener(new ListSelectionListener() {
             public void valueChanged(ListSelectionEvent e) {
                 //Ignore extra messages.
                 if (e.getValueIsAdjusting()) return;

                 ListSelectionModel lsm = (ListSelectionModel)e.getSource();
                 if (lsm.isSelectionEmpty()) {
                	 JOptionPane.showMessageDialog(frame, "No Row Selected!!!");
                 } else {
                     int selectedRow = lsm.getMinSelectionIndex();
                     
                     JOptionPane.showMessageDialog(frame, "User Name : "+users.get(selectedRow).getUserName());
                 }
             }
         });
		 
		
		 Border paneEdge = BorderFactory.createEmptyBorder(20,20,20,20);

		
		JScrollPane scrollPane = new JScrollPane(table);
		
		scrollPane.setPreferredSize(new Dimension(600, 200));
		scrollPane.setViewportView(table);
		scrollPane.setBorder(paneEdge);
		
		JLabel hbslebel=new JLabel("HBS Admin Control Panel");
		
		
		frame.add(hbslebel, BorderLayout.PAGE_START);
		
		frame.add(scrollPane, BorderLayout.CENTER);

		frame.setBackground(Color.LIGHT_GRAY);
		frame.setLocationRelativeTo(null);

		frame.setResizable(false);

		frame.setVisible(true);
	}
	
	private Object[][] createTableData(ArrayList<User> users){
		Object[][] resultData=null;
		
		resultData= new Object[users.size()][];
		
		for(int i=0;i<users.size();i++){
			
			 User row = users.get(i);
			 Object[] col={row.getUserName(),row.getUserPassword(),row.getUserEmail(),row.getUserType()};
			 resultData[i]=col;
			    
		}
		return resultData;
	}

}
