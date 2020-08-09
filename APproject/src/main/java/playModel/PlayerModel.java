package playModel;

import java.util.ArrayList;
import java.util.LinkedList;
import Cardspackage.Card;
import Cardspackage.Weapon;
import game.Deck;
import hero.Heros;
import interfaces.Visitor;
import passives.Passive;

public class PlayerModel {
	private String name;
	private Heros Hero;
	private int currentgem=1;
	private int previosgem=1;
	private Quest quest;
	private int Changes=0;
	private Weapon Weapon;
	private ArrayList<Card> Hand;
	private ArrayList<Card> deck;
	private int Decksize;
	private LinkedList<Card> battleGroundCard;
	private  int turn;
	private Passive passive;
	public Passive getPassive() {
		return passive;
	}
	public void setPassive(Passive passive) {
		this.passive = passive;
	}
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
	public PlayerModel(Deck deck, int tu, String name) {
		initial();
		turn=tu;
		this.name=name;
		Hero=deck.getHeroDeck();
		this.deck=(ArrayList<Card>) deck.getDeck().clone();
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
	public ArrayList<Card> getHand() {
		return Hand;
	}
	public void AddToHand(Card hand) {
		Hand.add(hand) ;
	}
	public ArrayList<Card> getDeck() {
		return deck;
	}
	public void AddToDeck(Card e) {
		deck.add(e) ;
	}
	public void setDeck(ArrayList<Card> deck) {
		this.deck = deck;
	}
	public int getDecksize() {
		return Decksize;
	}
	public void setDecksize(int decksize) {
		Decksize = decksize;
	}
	public LinkedList<Card> getBattleGroundCard() {
		return battleGroundCard;
	}
	public void setBattleGroundCard(LinkedList<Card> battleGroundCard) {
		this.battleGroundCard = battleGroundCard;
	}
	public void checkCard(PlayerModel enemy, Visitor v, Mapper mapper) {
			for (int i = 0; i < 7; i++) {
			if(battleGroundCard.get(i)!= null) {
				if(battleGroundCard.get(i).getHp()<=0) {
					battleGroundCard.get(i).accept(v, null,this, enemy,mapper);
					getBattleGroundCard().remove(i);
					getBattleGroundCard().add(i, null);									
				}
			}
		}			
	}
	public Quest getQuest() {
		return quest;
	}
	public void setQuest(Quest quest) {
		this.quest = quest;
	}
	public int getPreviosgem() {
		return previosgem;
	}
	public void setPreviosgem(int previosgem) {
		this.previosgem = previosgem;
	}
}
