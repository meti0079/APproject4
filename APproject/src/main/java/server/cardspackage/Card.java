package server.cardspackage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import server.gameModel.AbstractAdapter;
import server.interfaces.Acceptable;
import server.interfaces.Visitor;
@Entity
public abstract  class Card implements Acceptable {
	@Id
	private String name;
	@Column
	private boolean usedToAttack;
	@Column
	private boolean windfury=false;
	@Column
	private boolean taunt=false;
	@Column
	private boolean divineShield=false;
	@Column
	private boolean deathrattle=false;
	@Column
	private boolean battlecry=false;
	@Column
	private boolean rush=false;
	@Column
	private boolean quest;
	@Column
	private int mana;	
	@Column
	private int use;
	@Column
	private String rarity;
	@Column
	private String cardClass;
	@Column
	private String description;
	@Column
	private String type;
	
	
	public Card() {
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the mana
	 */
	public int getMana() {
		return mana;
	}
	/**
	 * @param mana the mana to set
	 */
	public void setMana(int mana) {
		this.mana = mana;
	}
	/**
	 * @return the rarity
	 */
	public String getRarity() {
		return rarity;
	}
	/**
	 * @param rarity the rarity to set
	 */
	public void setRarity(String rarity) {
		this.rarity = rarity;
	}
	/**
	 * @return the cardClass
	 */
	public String getCardClass() {
		return cardClass;
	}
	/**
	 * @param cardClass the cardClass to set
	 */
	public void setCardClass(String cardClass) {
		this.cardClass = cardClass;
	}
	/**
	 * @param use the use to set
	 */
	public void setUse(int use) {
		this.use = use;
	}
	public abstract int getAttack();
	public abstract void setAttack(int x);
	public abstract int getHp();
	public abstract void setHp(int x);
	public abstract String getType();
	
	
	
	
	
	public int getUse() {
		return use;
	}
	public void addUse() {
		this.use++;
	}
	public  boolean getUsedToAttack() {
		return usedToAttack;
	}
	public  void setUsedToAttack(boolean flag) {
		this.usedToAttack=flag;
	}
	public void setType(String type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void Set_Mana(int a) {
		mana=a;
	}
	public void Set_Name(String a) {
		name=a;
	}
	public boolean isTaunt() {
		return taunt;
	}	
	public void setTaunt(boolean taunt) {
		this.taunt = taunt;
	}

	public boolean isDivineShield() {
		return divineShield;
	}

	public void setDivineShield(boolean divineShield) {
		this.divineShield = divineShield;
	}
	public void Set_Rarity(String a) {
		rarity=a;
	}

	public boolean isWindfury() {
		return windfury;
	}

	public void setWindfury(boolean windfury) {
		this.windfury = windfury;
	}
	public void Set_Class(String a) {
		cardClass=a;
	}
	public int get_Mana() {
		return mana;
	}
	public String get_Name() {
		return name;
	}

	public boolean isQuest() {
		return quest;
	}

	public void setQuest(boolean quest) {
		this.quest = quest;
	}
	public boolean isRush() {
		return rush;
	}
	public void setRush(boolean rush) {
		this.rush = rush;
	}
	public String  get_Rarity() {
		return rarity;
	}
	public String get_Class() {
		return cardClass;
	}
	public boolean isDeathrattle() {
		return deathrattle;
	}

	public void setDeathrattle(boolean deathrattle) {
		this.deathrattle = deathrattle;
	}
	public boolean isBattlecry() {
		return battlecry;
	}

	public void setBattlecry(boolean battlecry) {
		this.battlecry = battlecry;
	}
	public int gemCost() {
		if(this.get_Rarity().equalsIgnoreCase("rare"))
			return 2;
		if(this.get_Rarity().equalsIgnoreCase("epic"))
			return 3;
		if(this.get_Rarity().equalsIgnoreCase("legendary"))
			return 4;
		if(this.get_Rarity().equalsIgnoreCase("common"))
			return 1;
		return 1;
	}
	public static LinkedList<Card> sortByUse(List<Card> list) {
		LinkedList< Card> sum=new LinkedList<>();
		ArrayList<Card> cop=(ArrayList<Card>) ((ArrayList<Card>)list).clone();
		int top=0;
		int index=0;
		for (int i = 0; i <10; i++) {			
			for (Card cards : cop) {
				if(cards.getUse()>top) {
					top=cards.getUse();
					index=list.indexOf(cards);
				}
			}
			if(sum.size()>0)
				if(sum.get(0).getUse()>cop.get(index).getUse())
					return sum;
			sum.add(cop.get(index));
			cop.remove(index);
			top=0;
			index=0;
		}
		return sum;

	}
	public static boolean compareUse(Card s,Card w) {
		if(s.getUse()==w.getUse())
			return true;
		return false;
	}
	public static Boolean compareRarity(Card s,Card w) {
		if(s.rarity.equalsIgnoreCase(w.rarity))
			return true;
		return false;

	}
	public static boolean compareMana(Card s,Card w) {
		if(s.mana==w.mana)
			return true;
		return false;

	}
	public static boolean compareType(Card s,Card w) {
		if(s.type.equalsIgnoreCase(w.type))
			return true;
		return false;
	}
	public  Card copy() {
		GsonBuilder gsonBilder=new GsonBuilder();
		gsonBilder.registerTypeAdapter(Card.class, new AbstractAdapter<Card>());
		gsonBilder.setPrettyPrinting();
		Gson gson=gsonBilder.create();
		File f=new File(System.getProperty("user.dir")+"\\src\\main\\java\\all cards\\"+name+".json");
		Scanner s = null;
		try {
			s = new Scanner(f);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		String se="";
		while(s.hasNext()) {
			se+=s.nextLine(); 
		}
		Card x= gson.fromJson(se, Card.class);
		if(x.get_Mana()!=this.get_Mana()) {
			x.Set_Mana(this.get_Mana());
		}
		if(x.getAttack()!=this.getAttack()) {
			x.setAttack(this.getAttack());
		}
		if(x.getHp()!=this.getHp()) {
			x.setHp(this.getHp());
		}
		return x;	
		
	}
	
}
