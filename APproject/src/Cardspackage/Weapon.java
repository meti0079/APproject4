package Cardspackage;


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

}
