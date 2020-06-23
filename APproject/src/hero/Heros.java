package hero;


public class Heros {

	private int HP=30;
	public void setHP(int hP) {
		HP = hP;
	}

	public String getHero_power() {
		return Hero_power;
	}

	public void setHero_power(String hero_power) {
		Hero_power = hero_power;
	}
	private String Hero_power;
	private String specialPower;
	private int useHeropower=1;
	private int HeroPowerMana=2;
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
	public void extra_Hero_Power() {
		useHeropower++;
	}
	public void Extra_HP(int a) {
		HP+=a;
	}

	public String get_SpecialPower() {
		return specialPower;
	}

	public int get_HP() {
		return	HP;
	}
	public int get_useheroPower() {
		return	useHeropower;
	}
	public void changeHeroPowerMana(int s) {
		HeroPowerMana=s;
	}
	public int getHeroPowerMana() {
		return HeroPowerMana;
	}
}
