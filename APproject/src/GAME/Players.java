package GAME;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import Cardspackage.Cards;
import hero.Heros;

public class Players {
	private String name;
	private String password;
	public  int gem;
	private Store myStore;
	private ArrayList<Decks> my_Decks=new ArrayList<>();
	private  ArrayList<Cards> my_Cards =new ArrayList<>();
	private ArrayList<Heros> my_Heros=new ArrayList<>();
	private int plays;
	private Decks myDeck;

	public Decks getMyDeck() {
		return myDeck;
	}

	public void setMyDeck(Decks myDeck) {
		this.myDeck = myDeck;
	}

	public Players(String name,String pass,int g) {
		this.name=name;
		password=pass;
		gem=g;
		setMyStore(new Store());
	}

	public void addHero(Heros s) {
		my_Heros.add(s);
	}
	public void change_Name(String s) {
		name=s;
	}
	public void change_Password(String s) {
		password=s;
	}
	public void add_card(Cards s) {
		my_Cards.add(s);
	}
	public void reduce_gem(int s) {
		if(gem-s>0)
			gem=gem-s;
	}
	public void increase_gem(int s) {
		gem=gem+s;
	}
	public String get_pass() {
		return password;	
	}
	public String get_name() {
		return name;	
	}
	public Heros get_hero() {
		return myDeck.getHeroDeck();
	}
	public ArrayList<Cards> get_mydeck() {
		return myDeck.getDeck();

	}
	public ArrayList<Cards> get_myCards() {
		return my_Cards;	
	}
	public ArrayList<Heros> get_myheros() {
		return my_Heros;	
	}
	public Store getMyStore() {
		return myStore;
	}
	public void setMyStore(Store mystore) {
		this.myStore = mystore;
	}
	public void buyaCard(Cards s) {
		my_Cards.add(this.myStore.getCard(s));
		myStore.getBuyCard().remove(s);		
	}
	public boolean sellaCard(Cards s) {
		for(Decks q: my_Decks) {
			for(Cards w : q.getDeck())
				if(w.get_Name().equals(s.get_Name())) {
					JOptionPane.showConfirmDialog(null, "cant sell because this card used in deck   :  "+q.getName()
					+"\n remove this from our deck", "cant sell",JOptionPane.ERROR_MESSAGE);
					return false;
				}	
		}
		my_Cards.remove(s);
		myStore.getBuyCard().add(s);
		return true;
	}
	public int getPlays() {
		return plays;
	}
	public void addPlays() {
		this.plays++;
	}
	public void adddeck(Decks a) {
		this.my_Decks.add(a);
	}
	public ArrayList<Decks> getalldeck() {
		return my_Decks;
	}

}