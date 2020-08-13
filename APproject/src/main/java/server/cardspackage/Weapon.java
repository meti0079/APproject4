package server.cardspackage;

import javax.persistence.Column;
import javax.persistence.Entity;

import server.hero.Heros;
import server.interfaces.Visitor;
import server.playModel.Mapper;
import server.playModel.PlayerModel;
@Entity
public abstract  class Weapon extends Card{
	@Column
	private int durability;
	@Column
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
	public boolean accept(Visitor v, Object taeget, PlayerModel attackerP, PlayerModel targetP, Mapper mapper) {
		if(taeget == null)
			return false;
		try {
			if(taeget instanceof Heros) {
				if(!mapper.checkTount(targetP)&& taeget.equals(targetP.getHero())) {
					((Heros) taeget).setHP(((Heros) taeget).get_HP()-this.getAttack());
					this.setHp(this.getHp()-1);
					return true;
				}	
			}else {	
				if(mapper.checkTount(targetP)&& mapper.validCard(targetP, (Card) taeget)) {
					if(taeget instanceof Minion &&((Minion) taeget).isTaunt()) {
						((Minion) taeget).setHp(((Minion) taeget).getHp()-this.getAttack());
						this.setHp(this.getHp()-1);
						attackerP.checkCard(targetP,v, mapper);
						targetP.checkCard(attackerP, v, mapper);
						return true;
					}else
						return false;
				}
				if (taeget instanceof Minion && mapper.validCard(targetP, (Card) taeget)){
					((Minion) taeget).setHp(((Minion) taeget).getHp()-this.getAttack());
					this.setHp(this.getHp()-1);	
					attackerP.checkCard(targetP,v, mapper);
					targetP.checkCard(attackerP, v, mapper);
					return true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}
