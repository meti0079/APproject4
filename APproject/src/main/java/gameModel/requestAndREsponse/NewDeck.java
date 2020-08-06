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
	/**
	 * @return the deckInfo
	 */
	public DeckInfo getDeckInfo() {
		return deckInfo;
	}
	/**
	 * @param deckInfo the deckInfo to set
	 */
	public void setDeckInfo(DeckInfo deckInfo) {
		this.deckInfo = deckInfo;
	}
	/**
	 * @return the tocken
	 */
	public int getTocken() {
		return tocken;
	}
	/**
	 * @param tocken the tocken to set
	 */
	public void setTocken(int tocken) {
		this.tocken = tocken;
	}
	public NewDeck() {
		// TODO Auto-generated constructor stub
	}
}
