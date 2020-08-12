package gameModel.requestAndREsponse;

import java.util.ArrayList;

import client.model.Card;
import server.hero.Heros;

public class ShopNeeds {
	ArrayList<Card> cards;
	ArrayList<Heros> buyHeros;
	ArrayList<Card> havenot;
	public ShopNeeds(ArrayList<Card> cards, ArrayList<Heros> buyHeros, ArrayList<Card> havenot) {
		super();
		this.cards = cards;
		this.buyHeros = buyHeros;
		this.havenot = havenot;
	}
	public ShopNeeds() {
	}
	public ArrayList<Card> getCards() {
		return cards;
	}
	public void setCards(ArrayList<Card> cards) {
		this.cards = cards;
	}
	public ArrayList<Heros> getBuyHeros() {
		return buyHeros;
	}
	public void setBuyHeros(ArrayList<Heros> buyHeros) {
		this.buyHeros = buyHeros;
	}
	public ArrayList<Card> getHavenot() {
		return havenot;
	}
	public void setHavenot(ArrayList<Card> havenot) {
		this.havenot = havenot;
	}
}