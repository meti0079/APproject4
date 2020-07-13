package Cardspackage.Minions;

import Cardspackage.Minion;
import interfaces.Acceptable;
import interfaces.Visitor;
import playModel.Player;

public class MurlocRaider extends Minion implements Acceptable{
	public MurlocRaider() {
		this.Set_Name("Murloc Raider");
		this.Set_Class("Neutral");
		this.Set_Rarity("common");
		this.Set_Mana(1);
		this.setHp(1);
		this.setAttack(2);	
	}

	@Override
	public boolean accept(Visitor v, Object taeget, Player attackerP, Player targetP) {
		super.accept(v, taeget, attackerP, targetP);
			v.visitMurlocRaider(this, taeget, attackerP, targetP);
			return true;
	}

}
