package server.hero;

import javax.persistence.Entity;

import server.cardspackage.Card;
import server.hero.heroPower.PriestPower;
import server.interfaces.AcceptHero;
import server.interfaces.HeroVisitor;
import server.playModel.PlayerModel;
@Entity
public class Priest extends Heros implements AcceptHero{

	public Priest() {
		this.setname("Priest");
		this.setHero_power(new PriestPower());
		this.set_SpecialPower("give 4 Hp to a minion or  a hero");

	}

	@Override
	public void accept(HeroVisitor v, PlayerModel p, int d, Card x) {
		v.visitPriest(p, d,x);
	}

	@Override
	public Heros clone() {
		Heros s= new Priest();
		s.setname(this.getname());
		s.set_SpecialPower(this.get_SpecialPower());
		s.setHero_power(this.getHero_power());
		s.setHP(this.get_HP());		
		return s;
	}
}
