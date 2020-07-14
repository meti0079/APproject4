package GAME;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import Cardspackage.Card;
import hero.Heros;

public class Player {
	private String name;
	private String password;
	public  int gem;
	private Store myStore;
	private ArrayList<Deck> my_Decks=new ArrayList<>();
	private  ArrayList<Card> my_Cards =new ArrayList<>();
	private ArrayList<Heros> my_Heros=new ArrayList<>();
	private int plays;
	private int currentDeck;

	public Player(String name,String pass,int g) {
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
	public void add_card(Card s) {
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
		return my_Decks.get(currentDeck).getHeroDeck();
	}
	public ArrayList<Card> get_mydeck() {
		return my_Decks.get(currentDeck).getDeck();

	}
	public ArrayList<Card> get_myCards() {
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
	public void buyaCard(Card s) {
		my_Cards.add(this.myStore.getCard(s));
		myStore.getBuyCard().remove(s);		
	}
	public boolean sellaCard(Card s) {
		for(Deck q: my_Decks) {
			for(Card w : q.getDeck())
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
	public void adddeck(Deck a) {
		this.my_Decks.add(a);
	}
	public ArrayList<Deck> getalldeck() {
		return my_Decks;
	}


	public Deck getMyDeck() {
		return my_Decks.get(currentDeck);
	}

	public void setMyDeck(int x) {
		this.currentDeck=x;
	}
	public ArrayList<Card> findHeroCard(String name){
		ArrayList<Card> sum=new ArrayList<>();
		for(Card s : my_Cards) {
			if(s.get_Class().contains(name)) {
				sum.add(s);		
			}
		}
		return sum;
	}
	public ArrayList<Card> findManaCard(int x){
		ArrayList<Card> sum=new ArrayList<>();
		for(Card s : my_Cards) {
			if(s.get_Mana()==x) {
				sum.add(s);		
			}
		}
		return sum;
	}
	public ArrayList<Card> findNameCard(String x){
		ArrayList<Card> sum=new ArrayList<>();
		for(Card s : my_Cards) {
			if(s.get_Name().contains(x)) {
				sum.add(s);		
			}
		}
		return sum;
	}
	public ArrayList<Deck> sortDecks() {
		Deck [] allDeck=new Deck[my_Decks.size()];
		int x=0;
		for(Deck s : getalldeck()) {
			allDeck[x]=s;
			x++;
		}
		for(int i=0;i<my_Decks.size()-1;i++) {
			for(int j=my_Decks.size()-i-1;j>0;j--) {
				if(allDeck[j].getWin()>=allDeck[j-1].getWin()) {	
					Deck y=allDeck[j-1];
					allDeck[j-1]=allDeck[j];
					allDeck[j]=y;
				}
			}
		}
		ArrayList<Deck> su=new ArrayList<>();
		for(Deck s : allDeck) {
			su.add(s);
		}
		return su;
	}
	public Card SpecialCard(String name ) {
		for (Card cards : my_Decks.get(currentDeck).getDeck()) {
			if (cards.get_Name().equalsIgnoreCase(name)) {
				return cards;
			}
		}
		return null;
	}
}