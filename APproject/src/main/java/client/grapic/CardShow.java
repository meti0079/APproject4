package client.grapic;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;
import client.model.Card;

public class CardShow extends JPanel  {

	private static final long serialVersionUID = 1L;
	private BufferedImage image;
	private Card card;
	public CardShow(Card card) {
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
		JLabel manaJLabel=new JLabel(card.getMana()+"");	
		manaJLabel.setBounds(10, 7, 20, 20);
		manaJLabel.setForeground(Color.WHITE);
		manaJLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		add(manaJLabel);
	}
	private void readImage() {
		File input_file ;
		if(!card.isUsedToAttack() || card.isRush()) {
			input_file = new File("src\\play image\\"+card.getName()+".png"); 
		}else {
			input_file = new File("src\\play image\\"+card.getName()+"1.png"); 			
		}
		image = new BufferedImage(100, 150, BufferedImage.TYPE_INT_ARGB);
		try {image = ImageIO.read(input_file);
		} catch (IOException e) {e.printStackTrace();}
	}
	private void initial() {
		setPreferredSize(new Dimension(100, 150));
		setSize(new Dimension(100, 150));
		setLayout(null);
		setBackground(new Color(0, 0, 0,0));
	}
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0, 0,this.getWidth(), this.getHeight(),null);
	}
	private void setAtackLable(Card x) {
		JLabel attackJLabel=new JLabel(x.getAttack()+"");
		attackJLabel.setBounds(10, 125, 20, 20);
		attackJLabel.setForeground(Color.WHITE);
		attackJLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		add(attackJLabel);
	}
	private void setHpLable(Card x) {
		JLabel hpJLabel=new JLabel(x.getHP()+"");
		hpJLabel.setBounds((this.getWidth()*4)/5, 125, 20, 20);
		hpJLabel.setForeground(Color.WHITE);
		hpJLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		add(hpJLabel);

	}
	private void setAtackLable1(Card x) {
		JLabel attackJLabel=new JLabel(x.getAttack()+"");
		attackJLabel.setBounds(10, 125, 20, 20);
		attackJLabel.setForeground(Color.WHITE);
		attackJLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		add(attackJLabel);
	}
	private void setDuribalityLable(Card x) {
		JLabel hpJLabel=new JLabel(x.getHP()+"");
		hpJLabel.setBounds(81, 125, 20, 20);
		hpJLabel.setForeground(Color.WHITE);
		hpJLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		add(hpJLabel);
	}
}
