package net.cloudcentrik.hbs;

import net.cloudcentrik.hbs.User;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.Border;

public class AdminView {

	public AdminView() {
		createAndShowGUI();
	}

	public void createAndShowGUI() {
		JFrame frame = new JFrame("HBS Admin");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		frame.setSize(800, 400);
		
		//get all user JSON request
		ArrayList<User> users=GetAllUser.getAllUser("http://localhost:9000/User/all");

		Object rowData[][]=createTableData(users);
		/*
		Object rowData[][] = {
				{ "ismail", "open123", "ismail@cloudcentrik.net", "admin" },
				{ "kadir", "Row2-Column2", "Row2-Column3", "shop" },
				{ "jishan", "Row2-Column2", "Row2-Column3", "shop" },
				{ "karim", "Row2-Column2", "Row2-Column3", "shop" },
				{ "jaman", "Row2-Column2", "Row2-Column3", "shop" }};
				*/
		
		Object columnNames[] = { "User Name", "Password", "Email", "User Type" };
		
		JTable table = new JTable(rowData, columnNames);
		table.setRowHeight(30);
		table.setBackground(Color.CYAN);
		table.setForeground(Color.blue);
		
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
