package hero;

import Cardspackage.Card;
import hero.heroPower.HeroPower;
import interfaces.HeroVisitor;
import playModel.PlayerModel;

public  abstract class Heros {

	private int HP=30;
	private HeroPower Hero_power;
	private String specialPower;
	private String name;
	
	public abstract void accept(HeroVisitor v, PlayerModel p, int d, Card x);
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
	public  abstract Heros clone();
	}
