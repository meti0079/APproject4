package Cardspackage;

import interfaces.Visitor;

public abstract  class Spell extends Cards{

	@Override
	public int getAttack() {
		return -1;
	}
//
	@Override
	public int getHp() {
		return -1;
	}
//
	@Override
	public void setAttack(int x) {
		
	}
//
	@Override
	public void setHp(int x) {		
	}

	@Override
	public String getType() {
		return "Spell";
	}
	@Override
	public Cards copy() {
		return null;
	}


	

}
