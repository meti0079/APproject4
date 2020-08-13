package server.hero;

import javax.persistence.Entity;

import server.cardspackage.Card;
import server.hero.heroPower.HunterPower;
import server.interfaces.AcceptHero;
import server.interfaces.HeroVisitor;
import server.playModel.PlayerModel;

@Entity
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
