package server.gameModel;

import server.cardspackage.Card;
import server.cardspackage.Minion;
import server.hero.Heros;
import server.interfaces.HeroPowerVisitor;
import server.playModel.PlayerModel;

public class ExportHeroPower implements HeroPowerVisitor{

	@Override
	public boolean visitMagePower(Object target, PlayerModel me, PlayerModel enemy) {
		if(target!=null) {
			if(target instanceof Heros && enemy.getHero().equals(target)) {
				((Heros)target).setHP(((Heros)target).get_HP()-2);
				return true;
			}else if(enemy.getBattleGroundCard().contains(target)){
				((Card)target).setHp(((Card)target).getHp()-2);
				return true;
			}	
		}
		return false;
	}
	@Override
	public boolean visitRougePower(Object target, PlayerModel me, PlayerModel enemy) {
		if(target==null) {
			me.getHand().add(enemy.getHand().get(0));
			enemy.getHand().remove(0);			
			return true;
		}	
		return false;
	}

	@Override
	public boolean visitHunterPower(Object target, PlayerModel me, PlayerModel enemy) {
		if(target instanceof Minion)
			if(enemy.getBattleGroundCard().contains(target)) {
				((Card) target).setHp(((Card) target).getHp()-1);
				return true;
			}
		return false;
	}

	@Override
	public boolean visitPriestPower(Object target, PlayerModel me, PlayerModel enemy) {
		if(target!=null)
			if(target.equals(me.getHero())) {
				((Heros)target).setHP(((Heros)target).get_HP()+2);	
				return true;
			}else if(me.getBattleGroundCard().contains(target)){
				((Card)target).setHp(((Card)target).getHp()+4);
				return true;
			}
		return false;
	}

	@Override
	public boolean visitWarlockPower(Object target, PlayerModel me, PlayerModel enemy) {
		for(int i=0;i<7;i++) {
			if(me.getBattleGroundCard().get(i)!=null) {
				me.getBattleGroundCard().get(i).setAttack(me.getBattleGroundCard().get(i).getAttack()+1);
				me.getBattleGroundCard().get(i).setHp(me.getBattleGroundCard().get(i).getHp()+1);
				return true;
			}
		}
		me.getHand().add(me.getDeck().get(0));
		me.getDeck().remove(0);
		return true;
	}
	
}
