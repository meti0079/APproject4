package GAME;

import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.JOptionPane;

import Cardspackage.Card;
import hero.Heros;


public class Decks {
	private  Heros heroDeck;
	private ArrayList<Card> deck;
	private String name;
	private int win;
	private int useThisDeck;

	public Decks() throws Exception {
		this.deck=new ArrayList<>();
		win=0;
		useThisDeck=0;
	}

	public float GetAverage() {
		int sum=0;
		for(Card a:deck)
			sum+=a.get_Mana();
		if(deck.size()==0)
			return 0;
		return sum/deck.size();
	}

	public Heros getHeroDeck() {
		return heroDeck;
	}
	public void setHeroDeck(String name) throws Exception {
		Gamestate game=Gamestate.getinsist();
		for(Heros s:game.getPlayer().get_myheros())
			if(s.getname().equalsIgnoreCase(name)) {
				this.heroDeck =s ;
			}
		for(Card a:deck) {
			if(!a.get_Class().equalsIgnoreCase("Neutral"))
				deck.remove(a);
		}
	}
	public ArrayList<Card> getDeck() {
		return deck;
	}
	public boolean addCardToDeck(Card e) {
		int sum=0;
		for(Card ss:deck) {
			if(ss.get_Name().equalsIgnoreCase(e.get_Name()))
				sum++;
		}
		if(sum<2) {
			if(e.get_Class().equalsIgnoreCase(heroDeck.getname()) || e.get_Class().equalsIgnoreCase("Neutral")) {
				if(deck.size()<15) {
					this.deck.add(e);
					return true;
				}else {
					JOptionPane.showConfirmDialog(null, "Cant add because this card is full  , \n remove a card from deck"
							, "error", JOptionPane.OK_CANCEL_OPTION);
				}
			}else {
				JOptionPane.showConfirmDialog(null, "Cant add because this card is for a diffrent hero , \n remove a card from deck"
						, "error", JOptionPane.OK_CANCEL_OPTION);
			}
		}else {
			JOptionPane.showConfirmDialog(null, "Cant add because you have this in your deck , \n remove a card from deck"
					, "error", JOptionPane.OK_CANCEL_OPTION);
		}
		return false;
	}
	/**
	 * @param deck the deck to set
	 */
	public void setDeck(ArrayList<Card> deck) {
		this.deck = deck;
	}

	public void removeCardFromDeck(Card e) {
		this.deck.remove(e);
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getWin() {
		return win;
	}
	public void addWin() {
		this.win++;
	}
	public void addWin(int a) {
		this.win=a;
	}
	public int getUsethisDeck() {
		return useThisDeck;
	}
	public void addUsethisDeck() {
		this.useThisDeck ++;
	}
	public void addUsethisDeck(int a) {
		this.useThisDeck =a;
	}
	public Card bestCard() {
		if(this.getDeck().size()<2)
			return null;
		LinkedList< Card > ee=Card.sortByUse(getDeck());			
		if(ee.size()==1) {
			return ee.get(0);
		}
		if(Card.compareUse(ee.get(0), ee.get(1))) {
			if(Card.compareRarity(ee.get(0), ee.get(1))){
				if(Card.compareMana(ee.get(0),ee.get(1))) {
					if(Card.compareType(ee.get(0),ee.get(1)))
						return ee.get(0);
				}else {
					if(ee.get(0).get_Mana()  >  ee.get(1).get_Mana())
						return ee.get(0);
					return ee.get(1);
				}
			}else {
				if(ee.get(0).get_Rarity().equalsIgnoreCase("legandry"))
					return ee.get(0);
				if(ee.get(1).get_Rarity().equalsIgnoreCase("legandry"))
					return ee.get(1);
				if(ee.get(0).get_Rarity().equalsIgnoreCase("epic"))
					return ee.get(0);
				if(ee.get(1).get_Rarity().equalsIgnoreCase("epic"))
					return ee.get(1);
				if(ee.get(0).get_Rarity().equalsIgnoreCase("rare"))
					return ee.get(0);
				if(ee.get(1).get_Rarity().equalsIgnoreCase("rare"))
					return ee.get(1);
			}
		}else {
			if(ee.get(0).getUse()  >  ee.get(1).getUse())
				return ee.get(0);
			return ee.get(1);
		}
		return  null;
	}
}
