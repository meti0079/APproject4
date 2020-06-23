package grapic;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private LoginPanel loginPanel;
	public MainFrame () throws Exception {
		super("HearthStone");
		setLayout(new BorderLayout());
		MenuPanel menupanel=new MenuPanel(this); 
		this.loginPanel=new LoginPanel(this,menupanel);
		add(loginPanel, BorderLayout.CENTER);
		setSize(new Dimension(1800, 1000));
		setResizable(false);
		pack();
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);		
		setVisible(true);
		///	set icon
		BufferedImage image = null; 
		try{ 
			File input_file = new File("stone.png"); 
			image = ImageIO.read(input_file); 
		} 
		catch(IOException e) 
		{	}
		setIconImage(image);
	}
}