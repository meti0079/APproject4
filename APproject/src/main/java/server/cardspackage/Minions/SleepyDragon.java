package server.cardspackage.Minions;

import server.cardspackage.Minion;
import server.interfaces.Visitor;
import server.playModel.Mapper;
import server.playModel.PlayerModel;
public class SleepyDragon extends Minion{
	public SleepyDragon() {
		this.Set_Name("Sleepy Dragon");
		this.Set_Class("Neutral");
		this.Set_Rarity("rare");
		this.Set_Mana(9);
		this.setHp(6);
		this.setAttack(6);	
	}

	@Override
	public boolean accept(Visitor v, Object taeget, PlayerModel attackerP, PlayerModel targetP, Mapper mapper) {
		v.visitSleepyDragon(this, taeget, attackerP, targetP);
		return super.accept(v, taeget, attackerP, targetP,mapper );
		}
}
