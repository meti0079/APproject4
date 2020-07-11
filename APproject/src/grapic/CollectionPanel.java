package grapic;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import GAME.Logger;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import Cardspackage.Card;
import GAME.Gamestate;
import myListeners.EnemyDeckListener;
import myListeners.LockCardListener;
import myListeners.MyDeckListener;
import myListeners.UnlockListener;

public class CollectionPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private JTabbedPane tp;
	private Collection_herospanel mage;
	private Collection_herospanel hunter;
	private Collection_herospanel priest;
	private Collection_herospanel rouge;
	private Collection_herospanel warlock;
	private Collection_herospanel netural;
	private Gamestate game;
	private Collection_herospanel myDeckPanel=new Collection_herospanel("deck");
	private Collection_herospanel enemyDeckPanel;	
	private Collection_deck deckbord=new Collection_deck(myDeckPanel,this);
	private ArrayList<JLabel > current = new ArrayList<>();
	private ArrayList<JLabel > enemyCurrent = new ArrayList<>();
	private Collection_search serchPanel;
	public CollectionPanel(MainFrame f) throws Exception {
		initial();
		initialPanels(f);
		setpanel(f);
	}
	private void initial() throws Exception {
		game=Gamestate.getinsist();
		setPreferredSize(new Dimension(1800,990));
	}
	private void initialPanels(MainFrame f) throws Exception {
		tp=new JTabbedPane();
		mage=new Collection_herospanel("mage");
		setCardToHeroPanel("Mage", mage,f);
		JScrollPane m=new JScrollPane(mage);
		mage.setPreferredSize(new Dimension(1500, 2000));
		hunter=new  Collection_herospanel("hunter");
		setCardToHeroPanel("Hunter", hunter,f);
		JScrollPane hu=new JScrollPane(hunter);
		hunter.setPreferredSize(new Dimension(1500, 2000));
		priest=new Collection_herospanel("priest");
		setCardToHeroPanel("Priest", priest,f);
		JScrollPane p=new JScrollPane(priest);
		priest.setPreferredSize(new  Dimension(1500, 2000));
		warlock=new Collection_herospanel("warlock");
		setCardToHeroPanel("Warlock", warlock,f);
		JScrollPane w=new JScrollPane(warlock);
		warlock.setPreferredSize(new  Dimension(1500, 2000));
		rouge=new Collection_herospanel("rogue");
		setCardToHeroPanel("Rouge", rouge,f);
		JScrollPane ru=new JScrollPane(rouge);
		rouge.setPreferredSize(new  Dimension(1500, 2000));
		netural=new Collection_herospanel("netural");
		setCardToHeroPanel("Neutral", netural,f);
		JScrollPane ne=new JScrollPane(netural);
		netural.setPreferredSize(new  Dimension(1500, 2500));
		setdeck();
		myDeckPanel.setPreferredSize(new Dimension(1500,2500));
		JScrollPane de=new JScrollPane(myDeckPanel);
		enemyDeckPanel=new Collection_herospanel("enemy");
		setEnemyDeck();
		enemyDeckPanel.setPreferredSize(new Dimension(1500, 2500));
		JScrollPane ed=new JScrollPane(enemyDeckPanel);
		setEnemyDeck();
		serchPanel=new Collection_search(f, deckbord, this);
		serchPanel.setPreferredSize(new Dimension(1500, 2000));
		JScrollPane ser=new JScrollPane(serchPanel);
		/////add to tabpane
		tp.add(m,"Mage");
		tp.add(hu,"Hunter");
		tp.add(p,"Priest");
		tp.add(w,"Warlock");
		tp.add(ru,"Rouge");
		tp.add(ne,"Neutral");
		tp.add(ser,"search");
		tp.add(de,"My Deck");
		tp.add(ed, "enemy deck");
		tp.setPreferredSize(new Dimension(1500, 790));
	}	
	private void setCardToHeroPanel(String name,JPanel p,MainFrame f) {
		for(Card s : game.getPlayer().findHeroCard(name)) {
			final JLabel lp =new JLabel(new ImageIcon( System.getProperty("user.dir")+"\\src\\card image\\"+s.get_Name()+".png"));
			lp.addMouseListener(new UnlockListener(s, this, deckbord));
			p.add(lp);
		}
		for(Card s2 : game.getPlayer().getMyStore().findHeroCard(name)) {
			final JLabel lp1 =new JLabel(new ImageIcon( System.getProperty("user.dir")+"\\src\\card image\\"+s2.get_Name()+"1.png"));
			lp1.addMouseListener(new LockCardListener(f, s2));
			p.add(lp1);
		}
	}
	private void removeDeckLablesFromPanel() {
		int sum= current.size();
		for (int i = sum-1; i >=0; i--) {
			myDeckPanel.remove(current.get(i));
			current.remove(i);
			myDeckPanel.update();
		}
	}	
	public void setdeck() {
		removeDeckLablesFromPanel();
		for(Card s : game.getPlayer().get_mydeck()) {			
			final JLabel lp =new JLabel(new ImageIcon( System.getProperty("user.dir")+"\\src\\card image\\"+s.get_Name()+".png"));
			lp.addMouseListener( new MyDeckListener(s, lp, this));
			myDeckPanel.add(lp);
			current.add(lp);
		}
	}
	public void setEnemyDeck(){
		removeEnemyLablesFromPanel();
		for(Card s : game.getEnemy().getEnemyDeck().getDeck()) {			
			final JLabel lp =new JLabel(new ImageIcon( System.getProperty("user.dir")+"\\src\\card image\\"+s.get_Name()+".png"));
			lp.addMouseListener( new EnemyDeckListener(s, lp, this));
			enemyDeckPanel.add(lp);
			enemyCurrent.add(lp);
		}
	}	
	public void makeChangeInEnemyDeck(Card s, JLabel lp) {
		try {
			Logger.getinsist().log(game.getPlayer().get_name(), "remove card from enemy deck", s.get_Name());
		} catch (IOException e1) {e1.printStackTrace();	}
		game.getEnemy().getEnemyDeck().getDeck().remove(s);
		enemyDeckPanel.remove(lp);
		enemyDeckPanel.update();
		deckbord.update();
	}	
	public void makeChangeInDeck(Card s, JLabel lp) {
		try {
			game.getPlayer().getMyDeck().addUsethisDeck(0);
			game.getPlayer().getMyDeck().addWin(0);
			Logger.getinsist().log(game.getPlayer().get_name(), "remove card from deck", s.get_Name());
		} catch (IOException e1) {e1.printStackTrace();}
		game.getPlayer().getMyDeck().getDeck().remove(s);	
		myDeckPanel.remove(lp);
		myDeckPanel.update();
		deckbord.update();
	}
	private void removeEnemyLablesFromPanel() {
		int sum= enemyCurrent.size();
		for (int i = sum-1; i >=0; i--) {
			enemyDeckPanel.remove(enemyCurrent.get(i));
			enemyCurrent.remove(i);
			enemyDeckPanel.update();
		}
	}
	private void setpanel(MainFrame f) throws Exception {
		setLayout(new BorderLayout());
		InfoPanel inf=InfoPanel.getinsist(f);
		add(inf,BorderLayout.NORTH);
		add(tp,BorderLayout.CENTER);
		add(deckbord,BorderLayout.EAST);
	}
}