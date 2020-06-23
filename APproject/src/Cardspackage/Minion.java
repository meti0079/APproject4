package Cardspackage;



public class Minion extends Cards{
	private int HP;
	private int Attack;
	public Minion() {
		this.setType("Minion");

	}

	public int getAttack() {
		return this.Attack;
	}

	@Override
	public int getHp() {
		return this.HP;
	}
//
	@Override
	public void setAttack(int x) {
		Attack=x;
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

}
