package playModel;

import java.awt.TextArea;
import java.util.Random;

import game.ExportHeroPower;
import interfaces.Visitor;

public class ComputerPlayer {
	private PlayerModel me;
	private PlayerModel enemy;
	private Visitor v;
	private TextArea textArea;
	public ComputerPlayer(PlayerModel me, PlayerModel enemy, Visitor v, TextArea textArea) {
		this.me = me;
		this.enemy = enemy;
		this.v = v;
		this.textArea = textArea;
	}
	private int randomPlace(int x) {
		if (x==1) {
			return Math.abs(new Random().nextInt(1000))+200;
		}else {
			return Math.abs(new Random().nextInt(200))+300;
		}
	}
//	public void addToBattlefield() {
//		try {
//			for(int i=enemy.getHand().size()-1;i>=0;i--) {
//				if(enemy.getHand().get(i).get_Mana()<=enemy.getCurrentgem()) {
//					Mapper.getinsist().addTobattleground(enemy.getHand().get(i), randomPlace(1), randomPlace(0), enemy,me,v, textArea);
//				}
//			}	
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//	public void attack() {
//		try {
//			for(int i=0;i<7;i++) {
//				if(enemy.getBattleGroundCard().get(i)!=null)
//				if(!enemy.getBattleGroundCard().get(i).getUsedToAttack() ||enemy.getBattleGroundCard().get(i).isRush()) {
//					if (FindTargetPlace(1)==0) {
//						Mapper.getinsist().handleAttack(enemy, me, v,750 ,760 ,enemy.getBattleGroundCard().get(i) );						
//					}else
//					Mapper.getinsist().handleAttack(enemy, me, v, FindTargetPlace(1), FindTargetPlace(2),enemy.getBattleGroundCard().get(i) );
//				}
//			}	
//		if(enemy.getWeapon()!=null && !enemy.getWeapon().getUsedToAttack())
//			Mapper.getinsist().handleAttack(enemy, me, v, 750, 760, enemy.getWeapon());
//		
//		if(enemy.getHero().getHero_power().getMana()<=enemy.getCurrentgem() ) {
//			Mapper.getinsist().handleHeroPower(enemy, me, new ExportHeroPower(), 750, 760, enemy.getHero().getHero_power(), v);
//		}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}	
//	}
	private int FindTargetPlace(int x) {
		for(int i=0;i<7;i++) {
			if(me.getBattleGroundCard().get(i)!=null) {
				if(x==1)
					return i*160+200;
				if(x==2)
					return 600;
			}
		}
		return 0;
	}
}
