package grapic;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.LayoutManager;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;

import hero.Heros;

public class HeroShow extends JPanel {

	private static final long serialVersionUID = -1330337979315858343L;
	private BufferedImage image;
	public HeroShow(Heros hero) {
		setPreferredSize(new Dimension(100, 150));
		setSize(new Dimension(100, 150));
		setLayout(null);
		setBackground(new Color(0, 0, 0, 0));
		File input_file = new File("src\\play image\\"+hero.getname()+".png"); 
		image = new BufferedImage(100, 150, 
				BufferedImage.TYPE_INT_ARGB);
		try {
			image = ImageIO.read(input_file);
		} catch (IOException e) {
			e.printStackTrace();
		} 


		JLabel hpJLabel=new JLabel(hero.get_HP()+"");
		hpJLabel.setBounds(150,160, 40, 40);
		hpJLabel.setForeground(Color.white);
		hpJLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
		add(hpJLabel);


	}
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0, 0,this.getWidth(), this.getHeight(),null);
		g.setColor(Color.red);
		g.fillOval(150, 160, 40, 40);
	}

}
