package client.grapic;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

import client.Controller;
import client.listeners.EnemyDeckListener;
import client.listeners.LockCardListener;
import client.listeners.MyDeckListener;
import client.listeners.UnlockListener;
import client.model.Card;
import client.model.DeckInfo;
import hero.Heros;


public class CollectionPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private JTabbedPane tp;
	private Collection_herospanel mage;
	private Collection_herospanel hunter;
	private Collection_herospanel priest;
	private Collection_herospanel rouge;
	private Collection_herospanel warlock;
	private Collection_herospanel netural;
	private Collection_herospanel myDeckPanel=new Collection_herospanel("deck");
	private Collection_herospanel enemyDeckPanel;	
	private Collection_deck deckbord;
	private ArrayList<JLabel > current = new ArrayList<>();
	private ArrayList<JLabel > enemyCurrent = new ArrayList<>();
	private Collection_search serchPanel;

	ArrayList<Card> have= new ArrayList<>();
	ArrayList<Card> dontHave= new ArrayList<>();
	ArrayList<Card> deck= new ArrayList<>();
	ArrayList<Card> enemyDeck= new ArrayList<>();
	ArrayList<DeckInfo> deckinfo= new ArrayList<>();
	String enemyHero="";
	ArrayList< Heros> heros=new ArrayList<>();


	public ArrayList<Heros> getHeros() {
		return heros;
	}
	public void setHeros(ArrayList<Heros> heros) {
		this.heros = heros;
	}

	public String getEnemyHero() {
		return enemyHero;
	}

	public void setEnemyHero(String enemyHero) {
		this.enemyHero = enemyHero;
	}
Controller controller;
	public CollectionPanel(Controller controller) throws Exception {
		 deckbord=new Collection_deck(myDeckPanel,this, controller);
		initial();
		this.controller=controller;
		initialPanels(controller);
		setpanel(controller);
	}
	private void initial() throws Exception {
		setPreferredSize(new Dimension(1800,990));
	}
	private void initialPanels(Controller controller) throws Exception {
		tp=new JTabbedPane();
		mage=new Collection_herospanel("mage");
		setCardToHeroPanel("Mage", mage);
		JScrollPane m=new JScrollPane(mage);
		mage.setPreferredSize(new Dimension(1500, 2000));
		hunter=new  Collection_herospanel("hunter");
		setCardToHeroPanel("Hunter", hunter);
		JScrollPane hu=new JScrollPane(hunter);
		hunter.setPreferredSize(new Dimension(1500, 2000));
		priest=new Collection_herospanel("priest");
		setCardToHeroPanel("Priest", priest);
		JScrollPane p=new JScrollPane(priest);
		priest.setPreferredSize(new  Dimension(1500, 2000));
		warlock=new Collection_herospanel("warlock");
		setCardToHeroPanel("Warlock", warlock);
		JScrollPane w=new JScrollPane(warlock);
		warlock.setPreferredSize(new  Dimension(1500, 2000));
		rouge=new Collection_herospanel("rogue");
		setCardToHeroPanel("Rouge", rouge);
		JScrollPane ru=new JScrollPane(rouge);
		rouge.setPreferredSize(new  Dimension(1500, 2000));
		netural=new Collection_herospanel("netural");
		setCardToHeroPanel("Neutral", netural);
		JScrollPane ne=new JScrollPane(netural);
		netural.setPreferredSize(new  Dimension(1500, 2500));
		setdeck();
		myDeckPanel.setPreferredSize(new Dimension(1500,2500));
		JScrollPane de=new JScrollPane(myDeckPanel);
		enemyDeckPanel=new Collection_herospanel("enemy");
		setEnemyDeck();
		enemyDeckPanel.setPreferredSize(new Dimension(1500, 2500));
		JScrollPane ed=new JScrollPane(enemyDeckPanel);
		serchPanel=new Collection_search( this, controller);
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
	public void updatePanel(Controller controller) {
		setCardToHeroPanel("Neutral", netural);
		setCardToHeroPanel("Rouge", rouge);
		setCardToHeroPanel("Warlock", warlock);
		setCardToHeroPanel("Mage", mage);
		setCardToHeroPanel("Hunter", hunter);
		setCardToHeroPanel("Priest", priest);
		setdeck();
		setEnemyDeck();
		deckbord.update(controller);
		repaint();
		revalidate();
	}

	private void setCardToHeroPanel(String name,JPanel p) {
		p.removeAll();
		for(Card s : findHeroCard(name, have)) {
			final JLabel lp =new JLabel(new ImageIcon( System.getProperty("user.dir")+"\\src\\main\\java\\card image\\"+s.getName()+".png"));
			lp.addMouseListener(new UnlockListener(s, this, controller));
			p.add(lp);
		}
		for(Card s2 : findHeroCard(name, dontHave)) {
			final JLabel lp1 =new JLabel(new ImageIcon( System.getProperty("user.dir")+"\\src\\main\\java\\card image\\"+s2.getName()+"1.png"));
			lp1.addMouseListener(new LockCardListener(controller.getUser().getTocken()));
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
		for(Card s : deck) {			
			final JLabel lp =new JLabel(new ImageIcon( System.getProperty("user.dir")+"\\src\\main\\java\\card image\\"+s.getName()+".png"));
			lp.addMouseListener( new MyDeckListener(s, controller.getUser().getTocken()));
			myDeckPanel.add(lp);
			current.add(lp);
		}
	}
	public void setEnemyDeck(){
		removeEnemyLablesFromPanel();
		for(Card s : enemyDeck) {			
			final JLabel lp =new JLabel(new ImageIcon( System.getProperty("user.dir")+"\\src\\main\\java\\card image\\"+s.getName()+".png"));
			lp.addMouseListener( new EnemyDeckListener(s, controller.getUser().getTocken()));
			enemyDeckPanel.add(lp);
			enemyCurrent.add(lp);
		}
	}	
	private void removeEnemyLablesFromPanel() {
		int sum= enemyCurrent.size();
		for (int i = sum-1; i >=0; i--) {
			enemyDeckPanel.remove(enemyCurrent.get(i));
			enemyCurrent.remove(i);
			enemyDeckPanel.update();
		}
	}
	private void setpanel(Controller controller) throws Exception {
		setLayout(new BorderLayout());
		InfoPanel inf=new InfoPanel(controller);
		add(inf,BorderLayout.NORTH);
		add(tp,BorderLayout.CENTER);
		add(deckbord,BorderLayout.EAST);
	}

	public ArrayList<Card> findHeroCard(String name, ArrayList<Card> cards){
		ArrayList<Card> sum=new ArrayList<>();
		for(Card s : cards) {
			if(s.getCardClass().contains(name)) {
				sum.add(s);		
			}
		}
		return sum;
	}
	public ArrayList<DeckInfo> getDeckinfo() {
		return deckinfo;
	}
	public void setDeckinfo(ArrayList<DeckInfo> deckinfo) {
		this.deckinfo = deckinfo;
	}
	public ArrayList<Card> getHave() {
		return have;
	}
	public void setHave(ArrayList<Card> have) {
		this.have = have;
	}
	public ArrayList<Card> getDontHave() {
		return dontHave;
	}
	public void setDontHave(ArrayList<Card> dontHave) {
		this.dontHave = dontHave;
	}
	public ArrayList<Card> getDeck() {
		return deck;
	}
	public void setDeck(ArrayList<Card> deck) {
		this.deck = deck;
	}
	public ArrayList<Card> getEnemyDeck() {
		return enemyDeck;
	}
	public void setEnemyDeck(ArrayList<Card> enemyDeck) {
		this.enemyDeck = enemyDeck;
	}
}