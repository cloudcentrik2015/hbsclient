package net.cloudcentrik.hbs;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class AdminView {

	public AdminView() {
		createAndShowGUI();
	}

	public void createAndShowGUI() {
		JFrame frame = new JFrame("HBS Admin");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.setSize(800, 400);

		Object rowData[][] = {
				{ "ismail", "open123", "ismail@cloudcentrik.net", "admin" },
				{ "kadir", "Row2-Column2", "Row2-Column3", "shop" },
				{ "jishan", "Row2-Column2", "Row2-Column3", "shop" },
				{ "karim", "Row2-Column2", "Row2-Column3", "shop" },
				{ "jaman", "Row2-Column2", "Row2-Column3", "shop" }};
		Object columnNames[] = { "User Name", "Password", "Email", "User Type" };
		JTable table = new JTable(rowData, columnNames);

		JScrollPane scrollPane = new JScrollPane(table);
		
		JLabel hbslebel=new JLabel("HBS Admin Control Panel");
		
		
		frame.add(hbslebel, BorderLayout.PAGE_START);
		
		frame.add(scrollPane, BorderLayout.CENTER);

		frame.setBackground(Color.CYAN);
		frame.setLocationRelativeTo(null);

		frame.setResizable(false);

		frame.setVisible(true);
	}

}
