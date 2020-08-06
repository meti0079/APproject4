package gameModel.requestAndREsponse;

public class AddCardToDeck {
	private int tocken;
	private String cardName;
	public AddCardToDeck(int tocken, String cardName) {
		super();
		this.tocken = tocken;
		this.cardName = cardName;
	}
	public AddCardToDeck() {
		// TODO Auto-generated constructor stub
	}
	public int getTocken() {
		return tocken;
	}
	public void setTocken(int tocken) {
		this.tocken = tocken;
	}
	public String getCardName() {
		return cardName;
	}
	public void setCardName(String cardName) {
		this.cardName = cardName;
	}

}
