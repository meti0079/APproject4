package Cardspackage;

import java.util.ArrayList;



public abstract  class Cards {
	private boolean Windfury=false;
	private boolean Taunt=false;
	private boolean DivineShield=false;
	private boolean Deathrattle=false;
	private boolean Battlecry=false;
	private boolean Rush=false;
	private boolean quest;
	private int Mana;
	private String Name;
	private String type;
	private int use;
	private String Rarity;
	private String Class;
	private String Description;
	
	
	
	
	public int getUse() {
		return use;
	}
	public void addUse() {
		this.use++;
	}
	
	public abstract int getAttack();
	public abstract void setAttack(int x);
	public abstract int getHp();
	public abstract void setHp(int x);
	public abstract String getType();
	
	public void setType(String type) {
		this.type = type;
	}
 
	public Cards() {
		
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public void Set_Mana(int a) {
		Mana=a;
	}
	public void Set_Name(String a) {
		Name=a;
	}
	public boolean isTaunt() {
		return Taunt;
	}	
	public void setTaunt(boolean taunt) {
		Taunt = taunt;
	}

	public boolean isDivineShield() {
		return DivineShield;
	}

	public void setDivineShield(boolean divineShield) {
		DivineShield = divineShield;
	}
	public void Set_Rarity(String a) {
		Rarity=a;
	}

	public boolean isWindfury() {
		return Windfury;
	}

	public void setWindfury(boolean windfury) {
		Windfury = windfury;
	}
	public void Set_Class(String a) {
		Class=a;
	}
	public int get_Mana() {
		return Mana;
	}
	public String get_Name() {
		return Name;
	}

	public boolean isQuest() {
		return quest;
	}

	public void setQuest(boolean quest) {
		this.quest = quest;
	}
	public boolean isRush() {
		return Rush;
	}
	public void setRush(boolean rush) {
		Rush = rush;
	}
	public String  get_Rarity() {
		return Rarity;
	}
	public String get_Class() {
		return Class;
	}
	public boolean isDeathrattle() {
		return Deathrattle;
	}

	public void setDeathrattle(boolean deathrattle) {
		Deathrattle = deathrattle;
	}
	public boolean isBattlecry() {
		return Battlecry;
	}

	public void setBattlecry(boolean battlecry) {
		Battlecry = battlecry;
	}
	
}
