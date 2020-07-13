package Cardspackage.Minions;

import Cardspackage.Minion;
import interfaces.Visitor;
import playModel.Player;
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
	public boolean accept(Visitor v, Object taeget, Player attackerP, Player targetP) {
		super.accept(v, taeget, attackerP, targetP);
		v.visitSleepyDragon(this, taeget, attackerP, targetP);
		return true;
	}
}
