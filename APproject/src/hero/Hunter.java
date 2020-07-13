package hero;

import Cardspackage.Card;
import hero.heroPower.HunterPower;
import interfaces.AcceptHero;
import interfaces.HeroVisitor;
import playModel.Player;

public class Hunter extends Heros implements AcceptHero{

	public Hunter() {
		this.setname("Hunter");
		this.setHero_power(new HunterPower());
		this.set_SpecialPower("attack 1 damage to each enemy when played whitout ane mana");

	}

	@Override
	public void accept(HeroVisitor v, Player p, int d, Card x) {
		v.visitHunter(p, d,x);
	}

}
