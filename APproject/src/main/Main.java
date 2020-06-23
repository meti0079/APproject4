
package main;

import grapic.MainFrame;
import javax.swing.SwingUtilities;

public class Main {

	public static void main(String[] args) throws Exception {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					new MainFrame();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});



//CLI s=new CLI();

	}

}
