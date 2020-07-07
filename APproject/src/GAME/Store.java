package GAME;
import java.util.ArrayList;
import Cardspackage.Cards;
import hero.Heros;

public class Store {

	private ArrayList< Cards> buyCard=new ArrayList<>();
	private ArrayList<Heros> buyHero=new ArrayList<>(); 
	
	public void setBuyCard(ArrayList< Cards> a) {
		buyCard=a;
	}
	public void setCardToBuyCard(Cards s) {
		buyCard.add(s);
	}
	public ArrayList<Cards> getBuyCard() {
		return buyCard;
	}
	public void removeCardFromBuyCard(Cards s) {
		buyCard.remove(s);
	}


	public  Cards getCard(Cards s) {
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
	public ArrayList<Cards> findHeroCard(String name){
		ArrayList<Cards> sum=new ArrayList<>();
		for(Cards s : buyCard) {
			if(s.get_Class().contains(name)) {
				sum.add(s);		
			}
		}
		return sum;
	}
	public ArrayList<Cards> findManaCard(int x){
		ArrayList<Cards> sum=new ArrayList<>();
		for(Cards s : buyCard) {
			if(s.get_Mana()==x) {
				sum.add(s);		
			}
		}
		return sum;
	}
	public ArrayList<Cards> findNameCard(String x){
		ArrayList<Cards> sum=new ArrayList<>();
		for(Cards s : buyCard) {
			if(s.get_Name().contains(x)) {
				sum.add(s);		
			}
		}
		return sum;
	}

}