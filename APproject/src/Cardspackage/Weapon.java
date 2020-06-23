package Cardspackage;

public class Weapon extends Cards{

	private int Durability;
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
		return Durability;
	}
	public void setDurability(int durability) {
		Durability = durability;
	}
	@Override
	public int getHp() {
		return Durability;
	}
	@Override
	public void setHp(int x) {
		Durability=x;		
	}

	@Override
	public String getType() {
		return "Weapon";
	}
}
