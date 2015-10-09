package net.cloudcentrik.hbs;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Hello world!
 *
 */
public class App 
{
static JFrame frame;    
public static void main( String[] args )
    {
// schedule this for the event dispatch thread (edt)
    SwingUtilities.invokeLater(new Runnable()
    {
      public void run()
      {
        displayJFrame();
      }
    });        


    }
 static void displayJFrame()
  {
    frame = new JFrame("HBS Client Example");
 
    // create our jbutton
    JButton showDialogButton = new JButton("GET REQUEST");
     
     
    // add the listener to the jbutton to handle the "pressed" event
    showDialogButton.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        // display/center the jdialog when the button is pressed
        /*JDialog d = new JDialog(frame,getRequest(), true);
        d.setLocationRelativeTo(frame);
        d.setVisible(true);*/
          //default title and icon
JOptionPane.showMessageDialog(frame,
    getRequest());

      }
    });
 
    // put the button on the frame
    frame.getContentPane().setLayout(new FlowLayout());
    frame.add(showDialogButton);
 
    // set up the jframe, then display it
    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    frame.setPreferredSize(new Dimension(300, 200));
    frame.pack();
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
  }
public static String getRequest(){
    
    String output="ERROR";

try {

			Client client = Client.create();

			WebResource webResource = client
					.resource("http://localhost:9000/");

			ClientResponse response = webResource.accept("application/json")
					.get(ClientResponse.class);

			if (response.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ response.getStatus());
			}

			output = response.getEntity(String.class);

			
			return output;

		} catch (Exception e) {

			e.printStackTrace();

		}
    finally{
        return output;
    }

}
}
