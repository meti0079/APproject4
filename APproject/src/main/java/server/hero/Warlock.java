package server.hero;

import server.cardspackage.Card;
import server.hero.heroPower.WarlockPower;
import server.interfaces.AcceptHero;
import server.interfaces.HeroVisitor;
import server.playModel.PlayerModel;

public class Warlock  extends Heros implements AcceptHero{
	public Warlock() {
		this.set_SpecialPower(" have 35 HP");;
		this.setHP(35);
		this.setname("Warlock");;
		this.setHero_power(new  WarlockPower());
	}
	@Override
	public void accept(HeroVisitor v, PlayerModel p, int d, Card x) {
		v.visitWarlock(p, d,x);
	}
	@Override
	public Heros clone() {
		Heros s= new Warlock();
		s.setname(this.getname());
		s.set_SpecialPower(this.get_SpecialPower());
		s.setHero_power(this.getHero_power());
		s.setHP(this.get_HP());		
		return s;
	}



	
}