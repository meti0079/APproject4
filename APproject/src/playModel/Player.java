package playModel;

import java.util.ArrayList;
import java.util.LinkedList;
import Cardspackage.Cards;
import Cardspackage.Weapon;
import GAME.Decks;
import hero.Heros;

public class Player {
	private String name;
	private Heros Hero;
	private int currentgem=1;
	private int Changes=0;
	private Weapon Weapon;
	private ArrayList<Cards> Hand;
	private ArrayList<Cards> deck;
	private int Decksize;
	private LinkedList<Cards> battleGroundCard;
	private  int turn;
	
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	private void initial() {
		battleGroundCard=new LinkedList<>();
		for (int i = 0; i <7; i++) {
			battleGroundCard.add(null);
		}
		Hand=new ArrayList<>();
	}
	public Player(Decks deck, int tu, String name) {
		initial();
		turn=tu;
		this.name=name;
		Hero=deck.getHeroDeck();
		this.deck=(ArrayList<Cards>) deck.getDeck().clone();
		Decksize=this.deck.size();
	}
	public int getTurn() {
		return turn;
	}
	public void setTurn(int turn) {
		this.turn = turn;
	}
	public Heros getHero() {
		return Hero;
	}

	public void setHero(Heros hero) {
		Hero = hero;
	}
	public int getCurrentgem() {
		return currentgem;
	}
	public void setCurrentgem(int currentgem) {
		this.currentgem = currentgem;
	}
	public int getChanges() {
		return Changes;
	}
	public void setChanges(int changes) {
		Changes = changes;
	}
	public Weapon getWeapon() {
		return Weapon;
	}
	public void setWeapon(Weapon weapon) {
		Weapon = weapon;
	}
	public ArrayList<Cards> getHand() {
		return Hand;
	}
	public void AddToHand(Cards hand) {
		Hand.add(hand) ;
	}
	public ArrayList<Cards> getDeck() {
		return deck;
	}
	public void AddToDeck(Cards e) {
		deck.add(e) ;
	}
	public void setDeck(ArrayList<Cards> deck) {
		this.deck = deck;
	}
	public int getDecksize() {
		return Decksize;
	}
	public void setDecksize(int decksize) {
		Decksize = decksize;
	}
	public LinkedList<Cards> getBattleGroundCard() {
		return battleGroundCard;
	}
	public void setBattleGroundCard(LinkedList<Cards> battleGroundCard) {
		this.battleGroundCard = battleGroundCard;
	}
}
