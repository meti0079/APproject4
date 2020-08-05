package gameModel.requestAndREsponse;

import java.util.ArrayList;

import client.model.DeckInfo;

public class StatosNeeds {
	ArrayList<DeckInfo> deck;
	public StatosNeeds(ArrayList<DeckInfo> deck) {
		super();
		this.deck = deck;
	}
	public StatosNeeds() {
	}
	public ArrayList<DeckInfo> getDeck() {
		return deck;
	}
	public void setDeck(ArrayList<DeckInfo> deck) {
		this.deck = deck;
	}

}
