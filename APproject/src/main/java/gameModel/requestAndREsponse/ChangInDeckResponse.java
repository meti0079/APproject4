package gameModel.requestAndREsponse;

import java.util.ArrayList;

import client.model.Card;
import client.model.DeckInfo;

public class ChangInDeckResponse {
	ArrayList<Card> deck;
	ArrayList<Card> Enemydeck;
	ArrayList<DeckInfo> deckInfo;
	public ChangInDeckResponse(ArrayList<Card> deck, ArrayList<Card> enemydeck, ArrayList<DeckInfo> deckInfo) {
		this.deck = deck;
		Enemydeck = enemydeck;
		this.deckInfo = deckInfo;
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
	public ChangInDeckResponse() {
	}
	
	
	
}
