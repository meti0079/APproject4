package hero;

import hero.heroPower.HeroPower;

public class Heros {

	private int HP=30;
	private HeroPower Hero_power;
	private String specialPower;
	private String name;
	public String getname() {
		return name;
	}
	public void setname(String n) {
		name=n;
	}
	public void set_SpecialPower(String s) {
		specialPower=s;
	}
	public void Extra_HP(int a) {
		HP+=a;
	}
	public void setHP(int hP) {
		HP = hP;
	}

	public HeroPower getHero_power() {
		return Hero_power;
	}

	public void setHero_power(HeroPower hero_power) {
		Hero_power = hero_power;
	}
	public String get_SpecialPower() {
		return specialPower;
	}

	public int get_HP() {
		return	HP;
	}
	public Heros clone() {
		Heros s= new Heros();
		s.setname(this.name);
		s.set_SpecialPower(this.specialPower);
		s.setHero_power(this.Hero_power);
		s.setHP(this.HP);
		return s;
	}
}
