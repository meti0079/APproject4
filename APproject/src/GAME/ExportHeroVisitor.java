package GAME;

import Cardspackage.Card;
import Cardspackage.Spell;
import interfaces.HeroVisitor;
import playModel.Player;

public class ExportHeroVisitor implements HeroVisitor{


	@Override
	public void visitMage(Player p, int d, Card x) {
		if(d==1)
		if(x instanceof Spell && d==1)
			x.Set_Mana(x.get_Mana()-2); 		
	}
	@Override
	public void visitRouge(Player p, int d, Card x) {
		if(d==1)
			if(!(x.get_Class().equalsIgnoreCase("rouge")||x.get_Class().equalsIgnoreCase("Neutral")))
				x.Set_Mana(x.get_Mana()-2);		
	}
	@Override
	public void visitHunter(Player p, int d, Card x) {		
	}
	@Override
	public void visitWarlock(Player p, int d, Card x) {		
	}
	@Override
	public void visitPriest(Player p, int d, Card x) {		
	}
}