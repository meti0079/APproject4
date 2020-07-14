package Cardspackage.Minions;

import Cardspackage.Minion;
import interfaces.Acceptable;
import interfaces.Visitor;
import playModel.PlayerModel;

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
	public boolean accept(Visitor v, Object taeget, PlayerModel attackerP, PlayerModel targetP) {
			v.visitChillwindYeti(this, taeget, attackerP, targetP);
			return super.accept(v, taeget, attackerP, targetP);
	}
}
