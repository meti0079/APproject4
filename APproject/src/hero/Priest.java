package hero;

import Cardspackage.Card;
import hero.heroPower.PriestPower;
import interfaces.AcceptHero;
import interfaces.HeroVisitor;
import playModel.Player;

public class Priest extends Heros implements AcceptHero{

	public Priest() {
		this.setname("Priest");
		this.setHero_power(new PriestPower());
		this.set_SpecialPower("give 4 Hp to a minion or  a hero");

	}

	@Override
	public void accept(HeroVisitor v, Player p, int d, Card x) {
		v.visitPriest(p, d,x);
	}
}
