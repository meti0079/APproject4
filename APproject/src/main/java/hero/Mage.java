package hero;

import java.util.ArrayList;

import Cardspackage.Card;
import hero.heroPower.MagePower;
import interfaces.AcceptHero;
import interfaces.HeroVisitor;
import playModel.PlayerModel;



public class Mage extends Heros implements AcceptHero{

	public Mage() {
		this.setname("Mage");
		this.setHero_power(new MagePower());
		this.set_SpecialPower("Spend 2 mana and damage 1 any enemy");

	}

	@Override
	public void accept(HeroVisitor v, PlayerModel p, int d, Card x) {
		v.visitMage(p, d,x);
	}

	@Override
	public Heros clone() {
		Heros s= new Mage();
		s.setname(this.getname());
		s.set_SpecialPower(this.get_SpecialPower());
		s.setHero_power(this.getHero_power());
		s.setHP(this.get_HP());		
		return s;	
		}

}
