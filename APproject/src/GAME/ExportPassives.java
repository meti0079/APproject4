package GAME;

import Cardspackage.Card;
import Cardspackage.Minion;
import Cardspackage.Weapon;
import grapic.PlayPanel;
import interfaces.PassiveVisitor;
import playModel.Player;

public class ExportPassives implements PassiveVisitor{
	@Override
	public void visitStargazing(Player me, Player enemy, Card x) {

	}

	@Override
	public void visitOffCard(Player me, Player enemy, Card x) {
		if(me.getHand().contains(x)) {
			x.Set_Mana(x.get_Mana()-1);
		}
	}
	@Override
	public void visitManaJump(Player me, Player enemy, Card x) {
		if(PlayPanel.getRoundGame()>57 && PlayPanel.getRoundGame()%2==me.getTurn()) {
			me.setPreviosgem(me.getPreviosgem()+1);
		}
	}	
	@Override
	public void visitAlchemistsStone(Player me, Player enemy, Card x) {
		if(x!=null)
		if(x.get_Mana()%2==1) {
			if(me.getBattleGroundCard().contains(x))
				for(Card s : me.getHand()) {
					s.Set_Mana(s.get_Mana()-1);	
				
				}
		}
	}
	@Override
	public void visitCaltrops(Player me, Player enemy, Card x) {
		if(x instanceof Minion)
			if(enemy.getBattleGroundCard().contains(x)) {
				x.setHp(x.getHp()-1);
			}
	}
	@Override
	public void visitCapturedFlag(Player me, Player enemy, Card x) {

		if(x instanceof Minion)
			if(me.getHand().contains(x)) {
				x.setAttack(x.getAttack()+1);
				x.setHp(x.getHp()+1);		
				}
	}
	@Override
	public void visitEntrenchment(Player me, Player enemy, Card x) {
		if(x instanceof Minion)
			if(me.getHand().contains(x)) {
				x.setHp(x.getHp()+2);
			}
	}
	@Override
	public void visitGlyphofWarding(Player me, Player enemy, Card x) {
		if(x!= null)
			if(x instanceof Minion)
				if(enemy.getHand().contains(x)) {
					x.Set_Mana(x.get_Mana()+1);
				}
	}
	@Override
	public void visitBandofScarabs(Player me, Player enemy, Card x) {
		if(enemy.getHand().contains(x))
			if(x instanceof Minion || x instanceof Weapon ) {
				x.setAttack(x.getAttack()-1);
			}
	}
	@Override
	public void visitGrommashsArmguards(Player me, Player enemy, Card x) {
		if(x instanceof Weapon )
			if(me.getHand().contains(x)) {
				x.Set_Mana(1);
			}
	}
}
