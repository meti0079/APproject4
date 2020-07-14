package Cardspackage;


public abstract  class Spell extends Card{

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
