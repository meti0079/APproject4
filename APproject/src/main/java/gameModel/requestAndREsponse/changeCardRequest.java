package gameModel.requestAndREsponse;

public class changeCardRequest {
	int tocken;
	String cardName;
	public changeCardRequest(int tocken, String cardName) {
		super();
		this.tocken = tocken;
		this.cardName = cardName;
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
