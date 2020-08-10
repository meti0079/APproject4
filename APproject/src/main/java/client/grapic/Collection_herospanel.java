package client.grapic;

import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Collection_herospanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private String name="";
	public Collection_herospanel(String name) {
		setPreferredSize(new Dimension(1500,1500));
		this.name+=name;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		drawBackGround(g);
	}
	private void drawBackGround(Graphics g) {
		ImageIcon icon =new ImageIcon("src\\main\\java\\backgrund image\\"+name+".jpg");
		g.drawImage(icon.getImage(), 0, 0, null);		
	}
	public void update() {
		this.repaint();
		this.revalidate();
	}
}
