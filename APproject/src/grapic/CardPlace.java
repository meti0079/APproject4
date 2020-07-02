package grapic;

import Cardspackage.Cards;

public class CardPlace {

	
	
	private Cards card;
	private int width;
	private int height;
	private int Wlength=100;
	private int Hlength=150;
	
	public CardPlace(Cards card, int x,int y) {
		this.card=card;
		this.width=x;
		this.height=y;
	}	
	public boolean isInPlace(int x, int y) {
		if(x<=width+Wlength && x>=width && y>=height && y<=height+Hlength)
			return true;
		return false;
	}
	public Cards getCard() {
		return card;
	}
	public void setCard(Cards card) {
		this.card = card;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}

}
