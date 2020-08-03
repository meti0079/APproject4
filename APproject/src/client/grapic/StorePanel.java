package  client.grapic;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import Cardspackage.Card;
import client.listeners.BuyCardListener;
import game.Gamestate;

public class StorePanel  extends JPanel{

	private static final long serialVersionUID = 1L;
	private Gamestate game;
	private InfoPanel inf;
	private Shop shop;
	private Collection_herospanel sellPanel;
	private ArrayList<JLabel> current=new ArrayList<>();
	public StorePanel(JFrame f, Shop shop, Collection_herospanel sell) throws Exception {
		this.shop=shop;
		sellPanel=sell;
		initial((MainFrame)f);
		updatePanel();
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
		for(Card s:game.getPlayer().getMyStore().getBuyCard()) {
			ImageIcon icon=new ImageIcon(System.getProperty("user.dir")+"\\src\\card image\\"+s.get_Name()+".png");
			final	JLabel lb1=new JLabel(icon);
			lb1.addMouseListener(new BuyCardListener(shop, this, lb1,s, inf, sellPanel)); 
			add(lb1);
			current.add(lb1);
		}
	}
	private void initial(MainFrame f) throws Exception {
		setPreferredSize(new Dimension(1800, 2000));
		setBackground(Color.BLUE);
		inf=InfoPanel.getinsist((MainFrame)f);
		game=Gamestate.getinsist();

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
		repaint();
		revalidate();
		updatePanel();
	}
}