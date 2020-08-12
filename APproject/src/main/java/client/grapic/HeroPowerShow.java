package client.grapic;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JLabel;

import server.cardspackage.Card;
import server.hero.Heros;
import server.hero.heroPower.HeroPower;

public class HeroPowerShow extends JPanel {
	private static final long serialVersionUID = 1L;
	private BufferedImage image;
	private Heros heroPower;
	public HeroPowerShow(Heros hero) {
		this.heroPower=hero;
		initial();
		readImage();
		manaLable();			
	}
	private void manaLable() {
		JLabel manaJLabel=new JLabel(heroPower.getHero_power().getMana()+"");	
		manaJLabel.setBounds(65,10, 30, 30);
		manaJLabel.setForeground(Color.WHITE);
		manaJLabel.setFont(new Font("Tahoma", Font.BOLD, 25));
		add(manaJLabel);
	}
	private void readImage() {
		File input_file = new File("src\\main\\java\\play image\\"+heroPower.getname()+"power.png"); 
		image = new BufferedImage(100, 150, BufferedImage.TYPE_INT_ARGB);
		try {image = ImageIO.read(input_file);
		} catch (IOException e) {e.printStackTrace();}
	}
	private void initial() {
		setPreferredSize(new Dimension(150,150	));
		setSize(new Dimension(150,150));
		setLayout(null);
		setBackground(new Color(0, 0, 0, 0));
	}
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0, 0,this.getWidth(), this.getHeight(),null);
	}
}
