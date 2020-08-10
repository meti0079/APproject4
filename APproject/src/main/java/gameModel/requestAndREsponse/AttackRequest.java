package gameModel.requestAndREsponse;

public class AttackRequest {
	int tocken,x,y;
	String cardName;
	public AttackRequest(int tocken, int x, int y, String cardName) {
		this.tocken = tocken;
		this.x = x;
		this.y = y;
		this.cardName = cardName;
	}
	public int getTocken() {
		return tocken;
	}
	public void setTocken(int tocken) {
		this.tocken = tocken;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public String getCardName() {
		return cardName;
	}
	public void setCardName(String cardName) {
		this.cardName = cardName;
	}

}
