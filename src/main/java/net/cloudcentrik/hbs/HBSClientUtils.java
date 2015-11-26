package net.cloudcentrik.hbs;

import javax.swing.UIManager;

public class HBSClientUtils {
	
	public static void setLookAndFeel() {

		try {
			UIManager
					.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
		} catch (Exception e) {
		}
	}

}
