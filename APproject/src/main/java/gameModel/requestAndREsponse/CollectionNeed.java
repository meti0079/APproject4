package gameModel.requestAndREsponse;

import java.util.ArrayList;
import client.model.Card;
import client.model.DeckInfo;
import server.hero.Heros;

public class CollectionNeed {
	ArrayList<Card> have;
	ArrayList<Card> dontHave;
	ArrayList<Card> deck;
	ArrayList<Card> Enemydeck;
	ArrayList<DeckInfo> deckInfo;
	ArrayList<Heros> hero;
	String enemyHero;
	public CollectionNeed() {
	}
	
	public CollectionNeed(ArrayList<Card> have, ArrayList<Card> dontHave, ArrayList<Card> deck,
			ArrayList<Card> enemydeck, ArrayList<DeckInfo> deckInfo, ArrayList<Heros> hero, String enemyHero) {
		super();
		this.have = have;
		this.dontHave = dontHave;
		this.deck = deck;
		Enemydeck = enemydeck;
		this.deckInfo = deckInfo;
		this.hero = hero;
		this.enemyHero = enemyHero;
	}
	public ArrayList<Heros> getHero() {
		return hero;
	}
	public void setHero(ArrayList<Heros> hero) {
		this.hero = hero;
	}
	public String getEnemyHero() {
		return enemyHero;
	}
	public void setEnemyHero(String enemyHero) {
		this.enemyHero = enemyHero;
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
	public ArrayList<Card> getEnemydeck() {
		return Enemydeck;
	}
	public void setEnemydeck(ArrayList<Card> enemydeck) {
		Enemydeck = enemydeck;
	}
	public ArrayList<DeckInfo> getDeckInfo() {
		return deckInfo;
	}
	public void setDeckInfo(ArrayList<DeckInfo> deckInfo) {
		this.deckInfo = deckInfo;
	}
	
}
