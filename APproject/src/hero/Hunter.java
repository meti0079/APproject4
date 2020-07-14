package hero;

import Cardspackage.Card;
import hero.heroPower.HunterPower;
import interfaces.AcceptHero;
import interfaces.HeroVisitor;
import playModel.PlayerModel;

public class Hunter extends Heros implements AcceptHero{

	public Hunter() {
		this.setname("Hunter");
		this.setHero_power(new HunterPower());
		this.set_SpecialPower("attack 1 damage to each enemy when played whitout ane mana");

	}

	@Override
	public void accept(HeroVisitor v, PlayerModel p, int d, Card x) {
		v.visitHunter(p, d,x);
	}

	@Override
	public Heros clone() {
		Heros s= new Hunter();
		s.setname(this.getname());
		s.set_SpecialPower(this.get_SpecialPower());
		s.setHero_power(this.getHero_power());
		s.setHP(this.get_HP());		
		return s;
	}

}
