package server.hero;

import java.util.ArrayList;

import server.cardspackage.Card;
import server.hero.heroPower.RougePower;
import server.interfaces.AcceptHero;
import server.interfaces.HeroVisitor;
import server.playModel.PlayerModel;


public class Rouge  extends Heros  implements AcceptHero{

	public Rouge() {
		this.setname("Rouge");
		this.set_SpecialPower("Spend 2 mana less other hero cards");
		this.setHero_power(new RougePower());
	}

	@Override
	public void accept(HeroVisitor v, PlayerModel p, int d, Card x) {
		v.visitRouge(p, d,x);	
	}

	@Override
	public Heros clone() {
		Heros s= new Rouge();
		s.setname(this.getname());
		s.set_SpecialPower(this.get_SpecialPower());
		s.setHero_power(this.getHero_power());
		s.setHP(this.get_HP());		
		return s;
	}






}
