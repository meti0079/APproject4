package Cardspackage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import game.AbstractAdapter;
import interfaces.Acceptable;
import interfaces.Visitor;

public abstract  class Card implements Acceptable {
	private boolean usedToAttack;
	private boolean windfury=false;
	private boolean taunt=false;
	private boolean divineShield=false;
	private boolean deathrattle=false;
	private boolean battlecry=false;
	private boolean rush=false;
	private boolean quest;
	private int mana;
	private String name;
	private int use;
	private String rarity;
	private String cardClass;
	private String description;
	private String type;
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

	public Card() {

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
	public static LinkedList<Card> sortByUse(ArrayList<Card > s) {
		LinkedList< Card> sum=new LinkedList<>();
		ArrayList<Card> cop=(ArrayList<Card>) s.clone();
		int top=0;
		int index=0;
		for (int i = 0; i <10; i++) {			
			for (Card cards : cop) {
				if(cards.getUse()>top) {
					top=cards.getUse();
					index=s.indexOf(cards);
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
