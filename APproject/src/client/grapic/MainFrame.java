package client.grapic;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private LoginPanel loginPanel;
	public MainFrame () throws Exception {
		super("HearthStone");
		initial();
		MenuPanel menupanel=new MenuPanel(this); 
		this.loginPanel=new LoginPanel(this,menupanel);
		add(loginPanel, BorderLayout.CENTER);	
		pack();
	}
	public void Update() {
		this.repaint();
		this.revalidate();
		this.pack();
	}
	private void initial() {
		setLayout(new BorderLayout());
		setSize(new Dimension(1800, 1000));
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);		
		setVisible(true);
		setIcon();
	}
	private void setIcon() {
		BufferedImage image = null; 
		try{ 
			File input_file = new File("stone.png"); 
			image = ImageIO.read(input_file); 
		} 
		catch(IOException e) {	}
		setIconImage(image);
	}
	public<T extends JPanel> void ChangePanel(JPanel T) {
		remove(getContentPane());
		setContentPane(T);
		revalidate();
		repaint();
		pack();
		setLocationRelativeTo(null);
	}
}