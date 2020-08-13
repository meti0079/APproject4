package server.cardspackage;

import javax.persistence.Entity;

@Entity
public abstract  class Spell extends Card{
public Spell() {
	// TODO Auto-generated constructor stub
}
	@Override
	public int getAttack() {
		return -1;
	}
	@Override
	public int getHp() {
		return -1;
	}
	@Override
	public void setAttack(int x) {	
	}
	@Override
	public void setHp(int x) {		
	}
	@Override
	public String getType() {
		return "Spell";
	}
}
