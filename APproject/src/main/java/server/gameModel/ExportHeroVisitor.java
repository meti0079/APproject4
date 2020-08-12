package server.gameModel;

import server.cardspackage.Card;
import server.cardspackage.Spell;
import server.interfaces.HeroVisitor;
import server.playModel.PlayerModel;

public class ExportHeroVisitor implements HeroVisitor{


	@Override
	public void visitMage(PlayerModel p, int d, Card x) {
		if(d==1)
		if(x instanceof Spell && d==1)
			x.Set_Mana(x.get_Mana()-2); 		
	}
	@Override
	public void visitRouge(PlayerModel p, int d, Card x) {
		if(d==1)
			if(!(x.get_Class().equalsIgnoreCase("rouge")||x.get_Class().equalsIgnoreCase("Neutral")))
				x.Set_Mana(x.get_Mana()-2);		
	}
	@Override
	public void visitHunter(PlayerModel p, int d, Card x) {		
	}
	@Override
	public void visitWarlock(PlayerModel p, int d, Card x) {		
	}
	@Override
	public void visitPriest(PlayerModel p, int d, Card x) {		
	}
}
