package server.gameModel;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import server.cardspackage.Card;
import server.hero.Heros;
@Entity
public class Player {
	@Id
	private String name;
	@Column
	private String password;
	@Column
	public  int gem;
	@Column
	private int plays;
	@Column
	private int currentDeck;
	@Column
	private int cup;
	@Column
	private int tocken;
	@OneToOne
	@Cascade(CascadeType.SAVE_UPDATE)
	private Store myStore;
	@ManyToMany
	@Cascade (CascadeType.SAVE_UPDATE)
	@LazyCollection(LazyCollectionOption.FALSE)
	@JoinTable(name = "Player_Deck")	
	private List<Deck> my_Decks=new ArrayList<>();
	@ManyToMany
	@Cascade (CascadeType.SAVE_UPDATE)
	@LazyCollection(LazyCollectionOption.FALSE)
	@JoinTable(name = "Player_Cards")	
	private  List<Card> my_Cards =new ArrayList<>();
	@ManyToMany
	@Cascade (CascadeType.SAVE_UPDATE)
	@LazyCollection(LazyCollectionOption.FALSE)
	@JoinTable(name = "Player_Hero")	
	private List<Heros> my_Heros=new ArrayList<>();


	public Player(String name,String pass,int g) {
		this.name=name;
		password=pass;
		gem=g;
		setMyStore(new Store());
	}
	public Player() {
		// TODO Auto-generated constructor stub
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getGem() {
		return gem;
	}
	public void setGem(int gem) {
		this.gem = gem;
	}
	public List<Deck> getMy_Decks() {
		return my_Decks;
	}
	public void setMy_Decks(List<Deck> my_Decks) {
		this.my_Decks = my_Decks;
	}
	public List<Card> getMy_Cards() {
		return my_Cards;
	}
	public void setMy_Cards(List<Card> my_Cards) {
		this.my_Cards = my_Cards;
	}
	public List<Heros> getMy_Heros() {
		return my_Heros;
	}
	public void setMy_Heros(List<Heros> my_Heros) {
		this.my_Heros = my_Heros;
	}
	public int getCurrentDeck() {
		return currentDeck;
	}
	public void setCurrentDeck(int currentDeck) {
		this.currentDeck = currentDeck;
	}
	public void setPlays(int plays) {
		this.plays = plays;
	}

	public Heros get_hero() {
		return my_Decks.get(currentDeck).getHeroDeck();
	}
	public List<Card> get_mydeck() {
		return my_Decks.get(currentDeck).getDeck();

	}
	public List<Card> get_myCards() {
		return my_Cards;	
	}
	public List<Heros> get_myheros() {
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
	public List<Deck> getalldeck() {
		return my_Decks;
	}
	public Deck getMyDeck() {
		return my_Decks.get(currentDeck);
	}
	public void setMyDeck(int x) {
		this.currentDeck=x;
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
				if(allDeck[j].getCup()>allDeck[j-1].getCup()) {	
					Deck y=allDeck[j-1];
					allDeck[j-1]=allDeck[j];
					allDeck[j]=y;
				}else if(allDeck[j].getCup()==allDeck[j-1].getCup()) {
					if(allDeck[j].getWin()>allDeck[j-1].getWin()) {
						Deck y=allDeck[j-1];
						allDeck[j-1]=allDeck[j];
						allDeck[j]=y;						
					}
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
	public int getCup() {
		return cup;
	}
	public void setCup(int cup) {
		this.cup = cup;
	}
	public int getTocken() {
		return tocken;
	}
	public void setTocken(int tocken) {
		this.tocken = tocken;
	}
}