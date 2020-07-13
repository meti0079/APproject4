package hero;

import Cardspackage.Card;
import hero.heroPower.WarlockPower;
import interfaces.AcceptHero;
import interfaces.HeroVisitor;
import playModel.Player;

public class Warlock  extends Heros implements AcceptHero{
	public Warlock() {
		this.set_SpecialPower(" have 35 HP");;
		this.setHP(35);
		this.setname("Warlock");;
		this.setHero_power(new  WarlockPower());
	}
	@Override
	public void accept(HeroVisitor v, Player p, int d, Card x) {
		v.visitWarlock(p, d,x);
	}



	
}