package grapic;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Collection_herospanel extends JPanel {
	private String name="";
	public Collection_herospanel(String name) {
		setPreferredSize(new Dimension(1500,1500));
		this.name+=name;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		ImageIcon icon =new ImageIcon("src\\backgrund image\\"+name+".jpg");
		g.drawImage(icon.getImage(), 0, 0, null);
	}
}
