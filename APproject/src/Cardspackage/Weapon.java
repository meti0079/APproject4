package Cardspackage;

import interfaces.Visitor;

public abstract  class Weapon extends Cards{

	private int durability;
	private int attack;
	public Weapon() {
		setType("Weapon");
	}
	public int getAttack() {
		return attack;
	}
	public void setAttack(int attack) {
		this.attack = attack;
	}
	public int getDurability() {
		return durability;
	}
	public void setDurability(int durability) {
		this.durability = durability;
	}
	@Override
	public int getHp() {
		return durability;
	}
	@Override
	public void setHp(int x) {
		durability=x;		
	}

	@Override
	public String getType() {
		return "Weapon";
	}
	@Override
	public Cards copy() {
//		Weapon s=new Weapon
//		s.setAttack(this.getAttack());
//		s.Set_Class(this.getClass()+"");
//		s.Set_Mana(this.get_Mana());
//		s.Set_Name(this.get_Name());
//		s.Set_Rarity(this.get_Rarity());
//		s.setBattlecry(this.isBattlecry());
//		s.setDeathrattle(this.isDeathrattle());
//		s.setDescription(this.getDescription());
//		s.setDivineShield(this.isDivineShield());
//		s.setQuest(this.isQuest());
//		s.setRush(this.isRush());
//		s.setTaunt(this.isTaunt());
//		s.setWindfury(this.isWindfury());
//		s.setDurability(this.getDurability());
		return null;	
	}

}
