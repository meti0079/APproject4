package GAME;

import Cardspackage.Card;
import Cardspackage.Minion;
import hero.Heros;
import interfaces.HeroPowerVisitor;
import playModel.Player;

public class ExportHeroPower implements HeroPowerVisitor{

	@Override
	public void visitMagePower(Object target, Player me, Player enemy) {
		if(target!=null) {
			if(target instanceof Heros && enemy.getHero().equals(target)) {
				((Heros)target).setHP(((Heros)target).get_HP()-2);
			}else if(enemy.getBattleGroundCard().contains(target)){
				((Card)target).setHp(((Card)target).getHp()-2);
				me.setCurrentgem(me.getCurrentgem()-2);
			}	
		}
	}
	@Override
	public void visitRougePower(Object target, Player me, Player enemy) {
		if(target==null) {
			me.getHand().add(enemy.getHand().get(0));
			enemy.getHand().remove(0);
			me.setCurrentgem(me.getCurrentgem()-3);
		}
	}

	@Override
	public void visitHunterPower(Object target, Player me, Player enemy) {
		if(target instanceof Minion)
			if(enemy.getBattleGroundCard().contains(target)) {
				((Card) target).setHp(((Card) target).getHp()-1);
			}		
	}

	@Override
	public void visitPriestPower(Object target, Player me, Player enemy) {
		if(target!=null)
			if(target.equals(me.getHero())) {
				((Heros)target).setHP(((Heros)target).get_HP()+2);	
				me.setCurrentgem(me.getCurrentgem()-2);
			}else if(me.getBattleGroundCard().contains(target)){
				((Card)target).setHp(((Card)target).getHp()+4);
				me.setCurrentgem(me.getCurrentgem()-2);
			}
	}

	@Override
	public void visitWarlockPower(Object target, Player me, Player enemy) {
		for(int i=0;i<7;i++) {
			if(me.getBattleGroundCard().get(i)!=null) {
				me.getBattleGroundCard().get(i).setAttack(me.getBattleGroundCard().get(i).getAttack()+1);
				me.getBattleGroundCard().get(i).setHp(me.getBattleGroundCard().get(i).getHp()+1);
				me.setCurrentgem(me.getCurrentgem()-2);
				return;
			}
		}
		me.getHand().add(me.getDeck().get(0));
		me.getDeck().remove(0);
		me.setCurrentgem(me.getCurrentgem()-2);
	}
}
