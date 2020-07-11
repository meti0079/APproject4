package Cardspackage.Minions;

import Cardspackage.Minion;
import interfaces.Acceptable;
import interfaces.Visitor;
import playModel.Player;

public class ChillwindYeti  extends Minion implements Acceptable{
	public ChillwindYeti() {
		this.Set_Name("Chillwind Yeti");
		this.Set_Class("Neutral");
		this.Set_Rarity("rare");
		this.Set_Mana(4);
		this.setHp(5);
		this.setAttack(4);	
	}

	@Override
	public boolean accept(Visitor v, Object taeget, Player attackerP, Player targetP) {
		if(super.accept(v, taeget, attackerP, targetP)) {
			v.visitChillwindYeti(this, taeget, attackerP, targetP);
			return true;
		}
		return false;
	}
}
