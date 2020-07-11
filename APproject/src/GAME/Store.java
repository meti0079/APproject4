package GAME;
import java.util.ArrayList;
import Cardspackage.Card;
import hero.Heros;

public class Store {

	private ArrayList< Card> buyCard=new ArrayList<>();
	private ArrayList<Heros> buyHero=new ArrayList<>(); 
	
	public void setBuyCard(ArrayList< Card> a) {
		buyCard=a;
	}
	public void setCardToBuyCard(Card s) {
		buyCard.add(s);
	}
	public ArrayList<Card> getBuyCard() {
		return buyCard;
	}
	public void removeCardFromBuyCard(Card s) {
		buyCard.remove(s);
	}


	public  Card getCard(Card s) {
		buyCard.remove(s);
		return s;
	}
	public void setBuyHero(ArrayList<Heros> a) {
		buyHero=a;
	}
	public void setHeroToBuyHero(Heros s) {
		buyHero.add(s);
	}
	public ArrayList<Heros> getBuyHero() {
		return buyHero;
	}
	public void RemoveHeroFromBuyHero(Heros s) {
		buyHero.remove(s);
	}


	public Heros getHero(Heros s) {
		buyHero.remove(s);
		return s;
	}
	public ArrayList<Card> findHeroCard(String name){
		ArrayList<Card> sum=new ArrayList<>();
		for(Card s : buyCard) {
			if(s.get_Class().contains(name)) {
				sum.add(s);		
			}
		}
		return sum;
	}
	public ArrayList<Card> findManaCard(int x){
		ArrayList<Card> sum=new ArrayList<>();
		for(Card s : buyCard) {
			if(s.get_Mana()==x) {
				sum.add(s);		
			}
		}
		return sum;
	}
	public ArrayList<Card> findNameCard(String x){
		ArrayList<Card> sum=new ArrayList<>();
		for(Card s : buyCard) {
			if(s.get_Name().contains(x)) {
				sum.add(s);		
			}
		}
		return sum;
	}

}