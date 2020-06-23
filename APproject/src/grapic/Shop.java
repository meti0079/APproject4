package grapic;

import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import Cardspackage.Cards;
import GAME.Gamestate;
import GAME.Logger;
import hero.Heros;
import myListeners.HeroBuyListener;
import myListeners.SellCardListener;


public class Shop extends JPanel{

	private static final long serialVersionUID = 1L;
	private Gamestate game;
	private InfoPanel inf;
	private Logger log;
	private ArrayList<JLabel> current=new ArrayList<>();
	public Shop(MainFrame f) throws Exception {
		setLayout(null);
		log=Logger.getinsist();
		inf = InfoPanel.getinsist(f);
		game=Gamestate.getinsist();
		setPreferredSize(new Dimension(1800, 1000));
		inf.setBounds(0, 0, 1800, 100);
		add(inf);
		Collection_herospanel sell= new Collection_herospanel("sell");
		sell.setPreferredSize(new Dimension(1800, 2000));
		JScrollPane sellPanel=new JScrollPane(sell);

		StorePanel storePanel=new StorePanel(f, this,sell);
		storePanel.setBounds(0, 100, 1800, 900);

		JScrollPane buyPanel=new JScrollPane(storePanel);

		JTabbedPane tp =new JTabbedPane();

		Collection_herospanel hero=new Collection_herospanel("deck1");
		setHero(hero);

		tp.add(buyPanel,"buy");
		tp.add(sellPanel, "sell");
		tp.add(hero,"hero");
		setCard(sell, storePanel);
		tp.setBounds(0, 100, 1800, 900);
		add(tp);
	}
	public void setCard(JPanel x, StorePanel y) {
		int sum= current.size();
		for (int i = sum-1; i >=0; i--) {
			x.remove(current.get(i));
			current.remove(i);
			x.repaint();
			x.revalidate();
		}
		for(Cards s:game.getPlayer().get_myCards()) {
			ImageIcon icon=new ImageIcon(System.getProperty("user.dir")+"\\src\\card image\\"+s.get_Name()+".png");
			final	JLabel lb1=new JLabel(icon);
			lb1.addMouseListener(new SellCardListener(y, x, lb1, inf, s, log, this));		 
			x.add(lb1);	
			current.add(lb1);
		}
	}

	public void setHero(Collection_herospanel x) {
		x.removeAll();
		for(Heros s:game.getPlayer().getMyStore().getBuyHero()) {
			ImageIcon icon=new ImageIcon(System.getProperty("user.dir")+"\\src\\play image\\"+s.getname()+".png");
			final	JLabel lb1=new JLabel(icon);
			lb1.addMouseListener(new HeroBuyListener(x, s, lb1, inf, log));		
			x.add(lb1);
		}
	}
	public int gemCost(Cards a) {
		if(a.get_Rarity().equalsIgnoreCase("rare"))
			return 2;
		if(a.get_Rarity().equalsIgnoreCase("epic"))
			return 3;
		if(a.get_Rarity().equalsIgnoreCase("legendary"))
			return 4;
		if(a.get_Rarity().equalsIgnoreCase("common"))
			return 1;
		return 1;
	}
}