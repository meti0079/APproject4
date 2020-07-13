package Cardspackage;

import Cardspackage.Minions.SecurityRover;
import hero.Heros;
import interfaces.Visitor;
import playModel.Mapper;
import playModel.Player;

public abstract  class Minion extends Card{
	private int HP;
	private int attack;
	public Minion() {
		this.setType("Minion");

	}

	public int getAttack() {
		return this.attack;
	}

	@Override
	public int getHp() {
		return this.HP;
	}
	//
	@Override
	public void setAttack(int x) {
		attack=x;
	}
	//
	@Override
	public void setHp(int x) {
		HP=x;
	}
	//
	@Override
	public String getType() {
		return "Minion" ;
	}
	@Override
	public boolean accept(Visitor v, Object taeget, Player attackerP, Player targetP) {
		if(taeget == null)
			return false;
		
		try {
			if(taeget instanceof Heros) {
				if(!Mapper.getinsist().checkTount(targetP)&& taeget.equals(targetP.getHero())) {
					((Heros) taeget).setHP(((Heros) taeget).get_HP()-this.getAttack());
					return true;
				}	
			}else {	
				if(Mapper.getinsist().checkTount(targetP)&&Mapper.getinsist().validCard(targetP, (Card) taeget)) {
					if(taeget instanceof Minion &&((Minion) taeget).isTaunt()) {
						((Minion) taeget).setHp(((Minion) taeget).getHp()-this.getAttack());
						this.setHp(this.getHp()-((Minion) taeget).getAttack());
						if(taeget instanceof SecurityRover)
							((SecurityRover)taeget).accept(v, null, targetP, attackerP);
						attackerP.checkCard(targetP,v);
						targetP.checkCard(attackerP, v);
						return true;
					}else
						return false;
				}
				if (taeget instanceof Minion &&Mapper.getinsist().validCard(targetP, (Card) taeget)){
					((Minion) taeget).setHp(((Minion) taeget).getHp()-this.getAttack());
					this.setHp(this.getHp()-((Minion) taeget).getAttack());	
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
