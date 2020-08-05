package  client.grapic;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import client.listeners.BuyCardListener;
import client.model.Card;

public class StorePanel  extends JPanel{

	private static final long serialVersionUID = 1L;
	private ArrayList<JLabel> current=new ArrayList<>();
	private ArrayList<Card> buyCard;
	
	
	public StorePanel() throws Exception {
		initial();
	}
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		setBackGround(g);
	}
	private void setBackGround(Graphics g) {
		g.drawImage(new ImageIcon("8.jpg").getImage(), 0, 0, null);		
	}
	public void updatePanel() {
		removeLables();
		for(Card s:buyCard) {
			ImageIcon icon=new ImageIcon(System.getProperty("user.dir")+"\\src\\card image\\"+s.getName()+".png");
			final	JLabel lb1=new JLabel(icon);
			lb1.addMouseListener(new BuyCardListener(s)); 
			add(lb1);
			current.add(lb1);
		}
	}
	private void initial() throws Exception {
		setPreferredSize(new Dimension(1800, 2000));
		setBackground(Color.BLUE);
	}
	private void removeLables() {
		int sum= current.size();
		for (int i = sum-1; i >=0; i--) {
			remove(current.get(i));
			current.remove(i);
		}
		repaint();
		revalidate();
	}
	public void update() {
		updatePanel();
		repaint();
		revalidate();
	}

	public ArrayList<Card> getBuyCard() {
		return buyCard;
	}

	public void setBuyCard(ArrayList<Card> buyCard) {
		this.buyCard = buyCard;
	}
}