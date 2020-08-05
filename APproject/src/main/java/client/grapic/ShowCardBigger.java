package client.grapic;

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
import javax.swing.JPanel;
import Cardspackage.Card;

public class ShowCardBigger extends JPanel  {

	private static final long serialVersionUID = 1L;
	private BufferedImage image;
	private Card card;
	public ShowCardBigger(Card card) {
		this.card=card;
		initial();
		readImage();
		if(card.getType().equalsIgnoreCase("minion")) {
			setAtackLable(card);
			setHpLable(card);		
		}else if(card.getType().equalsIgnoreCase("weapon")) {
			setAtackLable1(card);
			setDuribalityLable(card);
		}
		manaLable();			
	}
	private void manaLable() {
		JLabel manaJLabel=new JLabel(card.get_Mana()+"");	
		manaJLabel.setBounds(15, 8, 40, 40);
		manaJLabel.setForeground(Color.WHITE);
		manaJLabel.setFont(new Font("Tahoma", Font.BOLD, 25));
		add(manaJLabel);
	}
	private void readImage() {
		File input_file ;
		if(!card.getUsedToAttack() || card.isRush()) {
			input_file = new File("src\\play image\\"+card.get_Name()+".png"); 
		}else {
			input_file = new File("src\\play image\\"+card.get_Name()+"1.png"); 			
		}
		image = new BufferedImage(200, 300, BufferedImage.TYPE_INT_ARGB);
		try {image = ImageIO.read(input_file);
		} catch (IOException e) {e.printStackTrace();}
	}
	private void initial() {
		setPreferredSize(new Dimension(200, 300));
		setSize(new Dimension(200, 300));
		setLayout(null);
		setBackground(new Color(216, 178, 111));
	}
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0, 0,this.getWidth(), this.getHeight(),null);
	}
	private void setAtackLable(Card x) {
		JLabel attackJLabel=new JLabel(x.getAttack()+"");
		attackJLabel.setBounds(20, 250, 40, 40);
		attackJLabel.setForeground(Color.WHITE);
		attackJLabel.setFont(new Font("Tahoma", Font.BOLD,25));
		add(attackJLabel);
	}
	private void setHpLable(Card x) {
		JLabel hpJLabel=new JLabel(x.getHp()+"");
		hpJLabel.setBounds(170, 250, 30, 40);
		hpJLabel.setForeground(Color.WHITE);
		hpJLabel.setFont(new Font("Tahoma", Font.BOLD,25));
		add(hpJLabel);

	}
	private void setAtackLable1(Card x) {
		JLabel attackJLabel=new JLabel(x.getAttack()+"");
		attackJLabel.setBounds(20, 250, 40, 40);
		attackJLabel.setForeground(Color.WHITE);
		attackJLabel.setFont(new Font("Tahoma", Font.BOLD, 25));
		add(attackJLabel);
	}
	private void setDuribalityLable(Card x) {
		JLabel hpJLabel=new JLabel(x.getHp()+"");
		hpJLabel.setBounds(160, 250, 40, 40);
		hpJLabel.setForeground(Color.WHITE);
		hpJLabel.setFont(new Font("Tahoma", Font.BOLD, 25));
		add(hpJLabel);
	}
}
