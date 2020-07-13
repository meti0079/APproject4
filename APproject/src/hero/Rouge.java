package hero;

import java.util.ArrayList;

import Cardspackage.Card;
import hero.heroPower.RougePower;
import interfaces.AcceptHero;
import interfaces.HeroVisitor;
import playModel.Player;


public class Rouge  extends Heros  implements AcceptHero{

	public Rouge() {
		this.setname("Rouge");
		this.set_SpecialPower("Spend 2 mana less other hero cards");
		this.setHero_power(new RougePower());
	}

	@Override
	public void accept(HeroVisitor v, Player p, int d, Card x) {
		v.visitRouge(p, d,x);	
	}






}
