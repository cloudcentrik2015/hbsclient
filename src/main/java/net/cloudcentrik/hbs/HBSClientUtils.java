package net.cloudcentrik.hbs;

import javax.swing.BorderFactory;
import javax.swing.UIManager;
import javax.swing.border.Border;

public class HBSClientUtils {
	
	public static void setLookAndFeel() {

		try {
			UIManager
					.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
		} catch (Exception e) {
		}
	}
	
	public static Border createBorder(String name,int gap){
		Border emptyBorder = BorderFactory.createEmptyBorder(gap, gap, gap, gap);
		Border txtBorder = BorderFactory.createTitledBorder(
				BorderFactory.createEtchedBorder(),name);
		Border compoundBoarder = BorderFactory.createCompoundBorder(
				emptyBorder, txtBorder);
		return compoundBoarder;
	}

}
