package server.gameModel;

import client.grapic.PlayPanel;
import server.cardspackage.Card;
import server.cardspackage.Minion;
import server.cardspackage.Weapon;
import server.interfaces.PassiveVisitor;
import server.playModel.PlayerModel;

public class ExportPassives implements PassiveVisitor{
	@Override
	public void visitStargazing(PlayerModel me, PlayerModel enemy, Card x) {
		if(me.getHero().getHero_power().getMaxUse()!=2) {
			me.getHero().getHero_power().setMaxUse(2);
			me.getHero().getHero_power().setMana(me.getHero().getHero_power().getMana()-1);			
		}
	}

	@Override
	public void visitOffCard(PlayerModel me, PlayerModel enemy, Card x) {
		if(me.getHand().contains(x)) {
			x.Set_Mana(x.get_Mana()-1);
		}
	}
	@Override
	public void visitManaJump(PlayerModel me, PlayerModel enemy, Card x) {
//		if(PlayPanel.getRoundGame()>57 && PlayPanel.getRoundGame()%2==me.getTurn()) {
//			me.setPreviosgem(me.getPreviosgem()+1);
//		}
	}	
	@Override
	public void visitAlchemistsStone(PlayerModel me, PlayerModel enemy, Card x) {
		if(x!=null)
		if(x.get_Mana()%2==1) {
			if(me.getBattleGroundCard().contains(x))
				for(Card s : me.getHand()) {
					s.Set_Mana(s.get_Mana()-1);	
				
				}
		}
	}
	@Override
	public void visitCaltrops(PlayerModel me, PlayerModel enemy, Card x) {
		if(x instanceof Minion)
			if(enemy.getBattleGroundCard().contains(x)) {
				x.setHp(x.getHp()-1);
			}
	}
	@Override
	public void visitCapturedFlag(PlayerModel me, PlayerModel enemy, Card x) {

		if(x instanceof Minion)
			if(me.getHand().contains(x)) {
				x.setAttack(x.getAttack()+1);
				x.setHp(x.getHp()+1);		
				}
	}
	@Override
	public void visitEntrenchment(PlayerModel me, PlayerModel enemy, Card x) {
		if(x instanceof Minion)
			if(me.getHand().contains(x)) {
				x.setHp(x.getHp()+2);
			}
	}
	@Override
	public void visitGlyphofWarding(PlayerModel me, PlayerModel enemy, Card x) {
		if(x!= null)
			if(x instanceof Minion)
				if(enemy.getHand().contains(x)) {
					x.Set_Mana(x.get_Mana()+1);
				}
	}
	@Override
	public void visitBandofScarabs(PlayerModel me, PlayerModel enemy, Card x) {
		if(enemy.getHand().contains(x))
			if(x instanceof Minion || x instanceof Weapon ) {
				x.setAttack(x.getAttack()-1);
			}
	}
	@Override
	public void visitGrommashsArmguards(PlayerModel me, PlayerModel enemy, Card x) {
		if(x instanceof Weapon )
			if(me.getHand().contains(x)) {
				x.Set_Mana(1);
			}
	}
}
