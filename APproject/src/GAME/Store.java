package GAME;


import java.util.ArrayList;

import Cardspackage.Cards;
import GAME.Players;
import hero.Heros;
public class Store {

	private ArrayList< Cards> buyCard=new ArrayList<>();
	private ArrayList<Heros> buyHero=new ArrayList<>(); 


	public void setBuyCard(ArrayList< Cards> a) {
		buyCard=a;
	}
	public void setCardToBuyCard(Cards s) {
		buyCard.add(s);
	}
	public ArrayList<Cards> getBuyCard() {
		return buyCard;
	}
	public void removeCardFromBuyCard(Cards s) {
		buyCard.remove(s);
	}


	public  Cards getCard(Cards s) {
		buyCard.remove(s);
		return s;
	}
	public void setBuyHero(ArrayList<Heros> a) {
		buyHero=a;
	}
	public void setHeroToBuyHero(Heros s) {
		buyHero.add(s);
	}
	public ArrayList<Heros> getBuyHero() {
		return buyHero;
	}
	public void RemoveHeroFromBuyHero(Heros s) {
		buyHero.remove(s);
	}


	public Heros getHero(Heros s) {
		buyHero.remove(s);
		return s;
	}

}