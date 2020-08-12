package gameModel.requestAndREsponse;

import client.model.DeckInfo;

public class NewDeck {
	DeckInfo deckInfo;
	int tocken;
	public NewDeck(DeckInfo deckInfo, int tocken) {
		super();
		this.deckInfo = deckInfo;
		this.tocken = tocken;
	}
	public DeckInfo getDeckInfo() {
		return deckInfo;
	}
	public int getTocken() {
		return tocken;
	}
	public NewDeck() {
	}
}
