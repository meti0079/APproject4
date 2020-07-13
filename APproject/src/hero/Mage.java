package hero;

import java.util.ArrayList;

import Cardspackage.Card;
import hero.heroPower.MagePower;
import interfaces.AcceptHero;
import interfaces.HeroVisitor;
import playModel.Player;



public class Mage extends Heros implements AcceptHero{

	public Mage() {
		this.setname("Mage");
		this.setHero_power(new MagePower());
		this.set_SpecialPower("Spend 2 mana and damage 1 any enemy");

	}

	@Override
	public void accept(HeroVisitor v, Player p, int d, Card x) {
		v.visitMage(p, d,x);
	}

}
