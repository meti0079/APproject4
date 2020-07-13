package Cardspackage;

import hero.Heros;
import interfaces.Visitor;
import playModel.Mapper;
import playModel.Player;

public abstract  class Weapon extends Card{

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
	public boolean accept(Visitor v, Object taeget, Player attackerP, Player targetP) {
		if(taeget == null)
			return false;
		try {
			if(taeget instanceof Heros) {
				if(!Mapper.getinsist().checkTount(targetP)&& taeget.equals(targetP.getHero())) {
					((Heros) taeget).setHP(((Heros) taeget).get_HP()-this.getAttack());
					this.setHp(this.getHp()-1);
					return true;
				}	
			}else {	
				if(Mapper.getinsist().checkTount(targetP)&&Mapper.getinsist().validCard(targetP, (Card) taeget)) {
					if(taeget instanceof Minion &&((Minion) taeget).isTaunt()) {
						((Minion) taeget).setHp(((Minion) taeget).getHp()-this.getAttack());
						this.setHp(this.getHp()-1);
						attackerP.checkCard(targetP,v);
						targetP.checkCard(attackerP, v);
						return true;
					}else
						return false;
				}
				if (taeget instanceof Minion &&Mapper.getinsist().validCard(targetP, (Card) taeget)){
					((Minion) taeget).setHp(((Minion) taeget).getHp()-this.getAttack());
					this.setHp(this.getHp()-1);	
					attackerP.checkCard(targetP,v);
					targetP.checkCard(attackerP, v);
					return true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}
