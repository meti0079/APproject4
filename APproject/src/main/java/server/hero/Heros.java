package server.hero;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import server.cardspackage.Card;
import server.hero.heroPower.HeroPower;
import server.interfaces.HeroVisitor;
import server.playModel.PlayerModel;
@Entity
public  abstract class Heros {
	@Id
	private String name;
	@Column
	private int HP=30;
	@OneToOne (fetch = FetchType.EAGER)
	@Cascade(CascadeType.SAVE_UPDATE)
	@LazyCollection(LazyCollectionOption.FALSE)
	private HeroPower Hero_power;
	@Column
	private String specialPower;

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
	public Heros() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the specialPower
	 */
	public String getSpecialPower() {
		return specialPower;
	}
	/**
	 * @param specialPower the specialPower to set
	 */
	public void setSpecialPower(String specialPower) {
		this.specialPower = specialPower;
	}
	/**
	 * @return the hP
	 */
	public int getHP() {
		return HP;
	}
	public  abstract Heros clone();
}
