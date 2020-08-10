package client.grapic;

import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

import client.Controller;
import client.listeners.HeroBuyListener;
import client.listeners.SellCardListener;
import client.model.Card;
import hero.Heros;


public class Shop extends JPanel{

	private static final long serialVersionUID = 1L;
	private InfoPanel inf;
	private ArrayList<JLabel> current=new ArrayList<>();
//	private ArrayList<Card> myCards;
//	private ArrayList<Heros> heros;
	StorePanel storePanel;
	Collection_herospanel hero;
	Collection_herospanel sell;
	Controller controller;
	
	JScrollPane sellPanel;
	public StorePanel getStorePanel() {
		return storePanel;
	}

	public void setStorePanel(StorePanel storePanel) {
		this.storePanel = storePanel;
	}

//	public void setHeros(ArrayList<Heros> heros) {
//		this.heros = heros;
//	}
//
//	public void setMyCards(ArrayList<Card> myCards) {
//		this.myCards = myCards;
//	}

	private void initial() {
		setLayout(null);
		setPreferredSize(new Dimension(1800, 1000));
	}

	private void makePanels() throws Exception {
		sell= new Collection_herospanel("sell");
		sell.setPreferredSize(new Dimension(1800, 2000));
		 sellPanel=new JScrollPane(sell);
		storePanel=new StorePanel(controller);
		storePanel.setBounds(0, 100, 1800, 900);
		JScrollPane buyPanel=new JScrollPane(storePanel);
		JTabbedPane tp =new JTabbedPane();
		hero=new Collection_herospanel("deck1");
		tp.add(buyPanel,"buy");
		tp.add(sellPanel, "sell");
		tp.add(hero,"hero");
		tp.setBounds(0, 100, 1800, 900);
		add(tp);
	}
	public Shop(Controller controller) throws Exception {
		initial();
		this.controller=controller;
		inf = new InfoPanel(controller);
		inf.setBounds(0, 0, 1800, 100);
		add(inf);
		makePanels();
	}
	private void removeLables(JPanel x) {
		x.removeAll();
		current.removeAll(current);
//		int sum= current.size();
//		for (int i = sum-1; i >=0; i--) {
//			x.remove(current.get(i));
//			current.remove(i);
//		}
//		x.repaint();
//		x.revalidate();
	}
	public void setCard(ArrayList<Card> myCards) {
		removeLables(sell);
		for(Card s:myCards) {
			ImageIcon icon=new ImageIcon(System.getProperty("user.dir")+"\\src\\main\\java\\card image\\"+s.getName()+".png");
			final	JLabel lb1=new JLabel(icon);
			lb1.addMouseListener(new SellCardListener(s, controller.getUser().getTocken()));		 
			sell.add(lb1);	
			current.add(lb1);
		}
	}

	public void setHero(ArrayList<Heros> heros) {
		hero.removeAll();
		for(Heros s:heros) {
			ImageIcon icon=new ImageIcon(System.getProperty("user.dir")+"\\src\\main\\java\\play image\\"+s.getname()+".png");
			final	JLabel lb1=new JLabel(icon);
			lb1.addMouseListener(new HeroBuyListener(s, controller.getUser().getTocken()));		
			hero.add(lb1);
		}
	}
	public void update(ArrayList<Card > myCards, ArrayList<Heros > heros, ArrayList<Card> buyCard) {
		inf.repaint();
		inf.revalidate();
		setCard(myCards);
		setHero(heros);
		storePanel.update(buyCard);
		repaint();
		revalidate();

	}
}