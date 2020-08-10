package  client.grapic;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import client.Controller;
import client.listeners.BuyCardListener;
import client.model.Card;

public class StorePanel  extends JPanel{

	private static final long serialVersionUID = 1L;
	private ArrayList<JLabel> current=new ArrayList<>();
//	private ArrayList<Card> buyCard;
	
	Controller controller;
	public StorePanel(Controller controller) throws Exception {
		initial();
		this.controller=controller;
	}
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		setBackGround(g);
	}
	private void setBackGround(Graphics g) {
		g.drawImage(new ImageIcon("8.jpg").getImage(), 0, 0, null);		
	}
	public void updatePanel(ArrayList<Card> buyCard) {
		removeLables();
		for(Card s:buyCard) {
			ImageIcon icon=new ImageIcon(System.getProperty("user.dir")+"\\src\\main\\java\\card image\\"+s.getName()+".png");
			final	JLabel lb1=new JLabel(icon);
			lb1.addMouseListener(new BuyCardListener(s,controller.getUser().getTocken())); 
			add(lb1);
			current.add(lb1);
		}
		repaint();
		revalidate();
	}
	private void initial() throws Exception {
		setPreferredSize(new Dimension(1800, 2000));
		setBackground(Color.BLUE);
	}
	private void removeLables() {
		this.removeAll();
		current.removeAll(current);
		repaint();
		revalidate();
	}
	public void update(ArrayList<Card> buyCard) {
		updatePanel(buyCard);
		repaint();
		revalidate();
	}

//	public ArrayList<Card> getBuyCard() {
//		return buyCard;
//	}
//
//	public void setBuyCard(ArrayList<Card> buyCard) {
//		this.buyCard = buyCard;
//	}
}