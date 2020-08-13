package server.gameModel;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import server.cardspackage.Card;
import server.hero.Heros;
@Entity
public class Store {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private long id;
	
	@ManyToMany
	@Cascade (CascadeType.SAVE_UPDATE)
	@LazyCollection(LazyCollectionOption.FALSE)
	private List< Card> buyCard=new ArrayList<>();

	@ManyToMany
	@Cascade (CascadeType.SAVE_UPDATE)
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Heros> buyHero=new ArrayList<>(); 

	public void setBuyCard(ArrayList< Card> a) {
		buyCard=a;
	}
	public void setCardToBuyCard(Card s) {
		buyCard.add(s);
	}
	public List<Card> getBuyCard() {
		return buyCard;
	}
	public void removeCardFromBuyCard(Card s) {
		buyCard.remove(s);
	}
	public Store() {
		// TODO Auto-generated constructor stub
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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
	public List<Heros> getBuyHero() {
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